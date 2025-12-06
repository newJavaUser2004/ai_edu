package com.coder_mart_server.user.user_modules.user_teacher.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 在班级中操作学生dto
 */
@Getter
@Setter
@ToString
public class ClassActiveStudentDTO {
    //班级id
    Long classId;

    //学生id List
    List<Long> studentIdList;
}
