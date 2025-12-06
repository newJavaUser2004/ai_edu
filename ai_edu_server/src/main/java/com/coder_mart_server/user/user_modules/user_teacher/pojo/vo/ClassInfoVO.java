package com.coder_mart_server.user.user_modules.user_teacher.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 班级信息VO
 */
@Getter
@Setter
@ToString
public class ClassInfoVO implements Serializable {
    //班级id
    private Long classId;

    //班级名称
    private String className;

    //年级
    private Integer grade;

    //学生数量
    private Integer studentNum;

    //最大学生数量
    private Integer maxStudentNum;
}









