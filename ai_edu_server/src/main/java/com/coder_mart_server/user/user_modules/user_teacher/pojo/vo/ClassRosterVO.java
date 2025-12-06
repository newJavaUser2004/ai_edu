package com.coder_mart_server.user.user_modules.user_teacher.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 班级花名册VO
 */
@Getter
@Setter
@ToString
public class ClassRosterVO {
    //班级id
    Long classId;

    //学生信息
    List<StudentInfoVO> studentInfoVOList;
}
