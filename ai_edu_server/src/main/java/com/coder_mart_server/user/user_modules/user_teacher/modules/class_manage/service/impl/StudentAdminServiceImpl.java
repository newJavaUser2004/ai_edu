package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.impl;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.user.user_model.entity.ClassEntity;
import com.coder_mart_server.user.user_model.entity.ClassRosterEntity;
import com.coder_mart_server.user.user_modules.user_teacher.mappers.ClassMapper;
import com.coder_mart_server.user.user_modules.user_teacher.mappers.ClassRosterMapper;
import com.coder_mart_server.user.user_modules.user_teacher.mappers.RoleMapper;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.StudentAdminService;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassActiveStudentDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.StudentInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 学生管理业务
 * (拉学生进班级)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentAdminServiceImpl implements StudentAdminService {

    private final RoleMapper roleMapper;

    private final ClassRosterMapper classRosterMapper;

    private final ClassMapper classMapper;

    /**
     * 非线程安全地拉学生进班级
     * @param studentInvitedDTO
     * @return
     * 添加事务，避免脏读
     */
    @Transactional
    public List<ClassRosterEntity> invitedStudent(ClassActiveStudentDTO studentInvitedDTO){
        //检验class合法性（class是否存在、是否能操作该班级、是否满员）
        checkClassCouldInvited(studentInvitedDTO);

        //需要插入的信息实体map
        HashMap<Long, ClassRosterEntity> classRosterEntities = new HashMap<>();

        //检验学生：检测该用户是否为学生、检验该用户是否已经加入班级
        List<ClassRosterEntity> couldInsertStudentList = checkStudent(studentInvitedDTO, classRosterEntities);
        if(!couldInsertStudentList.isEmpty()) {
            //批量拉学生进班级（学生id存在，同时其身份为学生）
            Integer addStudentNum = classRosterMapper.insertStudentToClassRoster(couldInsertStudentList);

            //更新班级表中班级人数
            classMapper.updateClassStudentNum(studentInvitedDTO.getClassId(),addStudentNum);
        }

        //返回成功的用户List
        return couldInsertStudentList;
    }

    public void clearStudentFromClass(ClassActiveStudentDTO studentInvitedDTO){
        //检验class合法性
        ClassEntity classEntity = checkClass(studentInvitedDTO.getClassId());

        //todo 批量逻辑删除t_class_roster

        //todo 更新class学生数量
    }

    /**
     * 检验class合法性
     * @param classId
     * @return
     */
    public ClassEntity checkClass(Long classId){
        //获取班级数据，检验班级是否存在，计算插入后是否发生人数溢出
        ClassEntity classEntity = classMapper.selectClassByClassId(classId);
        if(classEntity == null){
            //抛出班级不存在异常
            throw new UserException(ResultEnum.CLASS_NULL_ERROR);
        }

        Long teacherId = ISecurity.getSecureUser().getUserId();
        if(classEntity.getTeacherId() != teacherId){
            //当前班级非本人操作
            throw new UserException(ResultEnum.ACTION_USER_ERROR);
        }

        return classEntity;
    }


    /**
     * 检验class是否可以添加新成员
     * @param studentInvitedDTO
     */
    public void checkClassCouldInvited(ClassActiveStudentDTO studentInvitedDTO){

        //检验class合法性
        ClassEntity classEntity = checkClass(studentInvitedDTO.getClassId());

        //检验是否有添加新成员的资格
        if(classEntity.getStudentNum() == classEntity.getMaxStudentNum()){
            //抛出人数已满
            throw new UserException(ResultEnum.CLASS_STUDENT_NUM_MAX_ERROR);
        }

        //计算添加后是否会满员
        if(classEntity.getStudentNum() + studentInvitedDTO.getStudentIdList().size() > classEntity.getMaxStudentNum()){
            //抛出添加人数超过最大班级人数限制
            throw new UserException(ResultEnum.CLASS_STUDENT_NUM_MAX_ERROR);
        }
    }


    /**
     * 检验用户合法性
     * @param studentInvitedDTO
     * @param classRosterEntities
     * @return 可以被插入的学生集合
     */
    private List<ClassRosterEntity> checkStudent(ClassActiveStudentDTO studentInvitedDTO,
                                                 HashMap<Long, ClassRosterEntity> classRosterEntities) {
        //能够插入的学生集合
        List<ClassRosterEntity> couldInsertStudentList = new ArrayList<>();

        //需要检验的学生id list
        ArrayList<Long> needCheckStudentIdList = new ArrayList<>();

        Long classId = studentInvitedDTO.getClassId();
        for(Long studentId : studentInvitedDTO.getStudentIdList()){
            ClassRosterEntity classRosterEntity = new ClassRosterEntity();
            classRosterEntity.setClassId(classId);
            classRosterEntity.setStudentId(studentId);

            needCheckStudentIdList.add(studentId);
            classRosterEntities.put(studentId,classRosterEntity);
        }

        //检验学生是否添加过
        List<Long> newNeedCheckStudentIdList = checkStudentIsInvited(
                classId,
                needCheckStudentIdList, classRosterEntities);

        //如果都没通过第一层质检，直接返回一个null值
        if(newNeedCheckStudentIdList.isEmpty()){
            return couldInsertStudentList;
        }

        //检验用户是否为学生
        Map<Long, ClassRosterEntity> couldInsertStudentMap = checkStudentRole(newNeedCheckStudentIdList,
                classRosterEntities);

        //第二层质检没过，返回null值
        if(couldInsertStudentMap.isEmpty()){
            return couldInsertStudentList;
        }

        couldInsertStudentList = new ArrayList(couldInsertStudentMap.values());

        return couldInsertStudentList;
    }

    /**
     * 检验学生是否添加过
     * @param classId
     * @param needCheckStudentIdList
     * @param classRosterEntities
     * @return
     */
    public List<Long> checkStudentIsInvited(Long classId, List<Long> needCheckStudentIdList,
                                            HashMap<Long, ClassRosterEntity> classRosterEntities){
        //需要被重新检验的学生id集合
        List<Long> newNeedCheckStudentIdList = needCheckStudentIdList;

        //查询出已经加入到该班级中的学生
        ClassRosterVO classRostByClassId = classRosterMapper.findClassRostByClassId(classId);

        //当前班级未添加学生，直接返回数据
        if(classRostByClassId == null){
            return newNeedCheckStudentIdList;
        }

        for(StudentInfoVO studentInfoVO : classRostByClassId.getStudentInfoVOList()){
            Long studentId = studentInfoVO.getStudentId();
            //如果已经加入，则从classRosterEntities中清除
            if(needCheckStudentIdList.contains(studentId)){
                classRosterEntities.remove(studentId);
                newNeedCheckStudentIdList.remove(studentId);
            }
        }

        return newNeedCheckStudentIdList;
    }

    /**
     * 检验用户的学生身份
     * @param needCheckStudentIdList
     * @return 可被插入的用户HashMap
     */
    public Map<Long, ClassRosterEntity> checkStudentRole(List<Long> needCheckStudentIdList,
                                       HashMap<Long, ClassRosterEntity> classRosterEntities){
        //从用户中，获取学生id集合
        List<Long> studentIdList = roleMapper.selectStudentFromUserIdList(needCheckStudentIdList);

        //数量相等，证明全是学生，则返回空的集合
        if(studentIdList.size() == needCheckStudentIdList.size()){
            return classRosterEntities;
        }

        //数量不等，证明有用户为非学生身份
        for(Long userId : needCheckStudentIdList){
            //如果不包含，则添加到noStudentIdList
            if(!studentIdList.contains(userId)){
                classRosterEntities.remove(userId);
            }
        }

        return classRosterEntities;
    }

}
