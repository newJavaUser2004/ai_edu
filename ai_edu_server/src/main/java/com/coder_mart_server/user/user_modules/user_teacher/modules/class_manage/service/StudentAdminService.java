package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service;

import com.coder_mart_server.user.user_model.entity.ClassRosterEntity;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassActiveStudentDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 学生管理业务
 */
public interface StudentAdminService {

    /**
     * 拉学生进班级
     * @param studentInvitedDTO
     * @return
     */
    List<ClassRosterEntity> invitedStudent(ClassActiveStudentDTO studentInvitedDTO);

    void clearStudentFromClass(ClassActiveStudentDTO studentInvitedDTO);
}
