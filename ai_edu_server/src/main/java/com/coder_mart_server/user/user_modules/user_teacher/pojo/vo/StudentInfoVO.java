package com.coder_mart_server.user.user_modules.user_teacher.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StudentInfoVO {
    //学生id
    Long studentId;

    //学生昵称
    String name;

    //学生年龄
    Integer age;

    //学生注册时间
    LocalDateTime createTime;

    //学生职位名称
    String roleName;

    //学生职位类型
    Integer roleType;

    //学生权限内容
    String roleContent;
}
