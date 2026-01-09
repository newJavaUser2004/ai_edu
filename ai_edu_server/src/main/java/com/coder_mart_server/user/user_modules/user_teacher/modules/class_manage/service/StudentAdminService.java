package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service;

import com.coder_mart_server.user.user_model.entity.ClassRosterEntity;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassActiveStudentDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.StudentInfoVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 学生管理业务
 */
public interface StudentAdminService {

    /**
     * 查询班级学生信息
     * @param classId 班级id
     * @return 学生信息集合
     */
    List<StudentInfoVO> getStudentInfo(Long classId);

    /**
     * 拉学生进班级
     * @param studentInvitedDTO
     * @return
     */
    List<ClassRosterEntity> invitedStudent(ClassActiveStudentDTO studentInvitedDTO);

    /**
     * 将学生踢出班级
     * @param studentInvitedDTO
     */
    void clearStudentFromClass(ClassActiveStudentDTO studentInvitedDTO);
}
