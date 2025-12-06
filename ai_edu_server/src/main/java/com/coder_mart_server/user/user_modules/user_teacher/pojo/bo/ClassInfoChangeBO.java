package com.coder_mart_server.user.user_modules.user_teacher.pojo.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ClassInfoChangeBO {
    //班级id
    private Long classId;

    //班级名称
    private String className;

    //年级
    private Integer grade;

    //最大学生数量
    private Integer maxStudentNum;

    //更新时间
    private LocalDateTime updateTime;
}
