package com.coder_mart_server.user.user_modules.user_teacher.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ClassInfoChangeDTO {
    //班级id
    private Long classId;

    //班级名称
    private String className;

    //年级
    private Integer grade;

    //最大学生数量
    private Integer maxStudentNum;
}
