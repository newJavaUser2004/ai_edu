package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.controller;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.public_modules.util.RedisUtil;
import com.coder_mart_server.public_modules.util.RedissonUtil;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import com.coder_mart_server.security.security_modules.authorizer.constant.PermissionsConstant;
import com.coder_mart_server.user.user_model.entity.ClassRosterEntity;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.constant.ClassManageRedisKeyConstant;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.StudentAdminService;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassActiveStudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/student/admin")
@Slf4j
public class StudentAdminController {
    //学生管理服务
    private final StudentAdminService studentAdminService;

    /**
     * 拉学生进班级
     * @param studentInvitedDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/invited")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result<List<ClassRosterEntity>> invitedStudent(@RequestBody ClassActiveStudentDTO studentInvitedDTO) throws Exception {
        //确保参数无问题
        if(studentInvitedDTO.getClassId() == null
                || studentInvitedDTO.getStudentIdList() == null
                || studentInvitedDTO.getStudentIdList().isEmpty()){
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        //加锁(lockName为classId，对不同的班级进行操作，不会产生线程安全问题)
        String lockName = RedisUtil.buildKey(ClassManageRedisKeyConstant.STUDENT_INVITED_LOCK_KEY,
                studentInvitedDTO.getClassId());

        //线程安全地拉学生进班级
        List<ClassRosterEntity> classRosterEntities = RedissonUtil.tryLock(lockName,
                () -> {
                    return studentAdminService.invitedStudent(studentInvitedDTO);
                });

        return Result.success(classRosterEntities);
    }

    //踢学生出班级
    @DeleteMapping("/clear")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result<String> clearStudent(@RequestBody ClassActiveStudentDTO studentInvitedDTO){
        studentAdminService.clearStudentFromClass(studentInvitedDTO);
        return Result.success();
    }

    //给学生安排职务
    @PutMapping("/assigned")
    public Result<String> assignedStudent(){
        return Result.success();
    }
}




























