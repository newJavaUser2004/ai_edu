package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.impl;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.helppers.UniqueIdHelpper;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.model.enums.StringEnum;
import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.user.user_constant.EntityDefaultConstant;
import com.coder_mart_server.user.user_model.entity.ClassEntity;
import com.coder_mart_server.user.user_modules.user_teacher.mappers.ClassMapper;
import com.coder_mart_server.user.user_modules.user_teacher.mappers.ClassRosterMapper;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.ClassAdminService;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.bo.ClassInfoChangeBO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoChangeDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassInfoVO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级管理业务
 * （获取班级信息、班级学生信息、添加班级、删除班级、修改班级）
 */
@Service
@RequiredArgsConstructor
public class ClassAdminServiceImpl implements ClassAdminService {

    //t_class表对应mapper
    private final ClassMapper classMapper;

    //t_class_roster表对应mapper
    private final ClassRosterMapper classRostMapper;

    //唯一id生成器
    private final UniqueIdHelpper uniqueIdHelpper;

    /**
     * 根据老师id，批量获取班级信息
     * @param teacherId
     * @return
     */
    public List<ClassInfoVO> findClassInfoList(Long teacherId){
        List<ClassInfoVO> classInfoList = classMapper.findClassInfoListByTeacherId(teacherId);
        return classInfoList;
    }

    /**
     * 根据班级id，查询班级花名册
     * @param classId
     * @return
     */
    public ClassRosterVO findClassRoster(Long classId){
        ClassRosterVO classRost = classRostMapper.findClassRostByClassId(classId);
        return classRost;
    }

    /**
     * 添加班级
     * @param classInfoDTOS
     */
    public void addClassInfo(List<ClassInfoDTO> classInfoDTOS){
        ArrayList<ClassEntity> classEntities = new ArrayList<>();

        //获取老师id
        Long teacherId = ISecurity.getSecureUser().getUserId();

        for(ClassInfoDTO classInfoDTO : classInfoDTOS){
            ClassEntity classEntity = new ClassEntity();
            copyNotNullProperties(classInfoDTO,classEntity);

            //设置唯一班级id
            Long classId = uniqueIdHelpper.snowIdBuild();
            classEntity.setClassId(classId);
            //设置班级老师id
            classEntity.setTeacherId(teacherId);

            classEntities.add(classEntity);
        }

        //保存班级信息
        classMapper.insertClassInfo(classEntities);
    }

    /**
     * 将非null值进行赋值，有默认值的null值使用默认值
     * @param classInfoDTO
     * @param classEntity
     */
    private void copyNotNullProperties(ClassInfoDTO classInfoDTO,ClassEntity classEntity){
        String className = classInfoDTO.getClassName();
        //className不能为null或者为""
        if(className == null || (StringEnum.EMPTY.getValue()).equals(className)){
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        //年级需要保证不为null并且>0
        Integer grade = classInfoDTO.getGrade();
        if(grade == null || grade <= 0){
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        //设置默认的学生数量为0
        classEntity.setStudentNum(EntityDefaultConstant.ClassEntityConstant.DEFAULT_STUDENT_NUM);

        //最大学生数量不能为null，且必须>0；不满足则使用默认值
        Integer maxStudentNum = classInfoDTO.getMaxStudentNum();
        if(maxStudentNum == null || maxStudentNum <= 0){
            classEntity.setClassName(className);
            classEntity.setGrade(grade);
            //设置默认的最大学生数量
            classEntity.setStudentNum(EntityDefaultConstant.ClassEntityConstant.DEFAULT_STUDENT_NUM);
            classEntity.setMaxStudentNum(EntityDefaultConstant.ClassEntityConstant.DEFAULT_MAX_STUDENT_NUM);
            return;
        }

        //所有参数无问题，直接克隆
        BeanUtils.copyProperties(classInfoDTO,classEntity);
    }

    /**
     * 逻辑删除班级id
     * @param classIdList
     */
    public void deleteClassInfo(List<Long> classIdList){
        //逻辑删除班级
        classMapper.deleteClassInfo(classIdList);

        //逻辑删除班级花名册
        classRostMapper.deleteClassRoster(classIdList);
    }

    /**
     * 修改班级信息
     * @param classInfoChangeDTO
     */
    public void changeClassInfo(ClassInfoChangeDTO classInfoChangeDTO){
        //创建更新BO
        ClassInfoChangeBO classInfoChangeBO = new ClassInfoChangeBO();
        BeanUtils.copyProperties(classInfoChangeDTO,classInfoChangeBO);

        //设置更新时间
        classInfoChangeBO.setUpdateTime(LocalDateTime.now());

        //根据BO修改班级信息
        classMapper.changeClassInfo(classInfoChangeBO);
    }
}
















