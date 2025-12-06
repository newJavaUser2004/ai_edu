package com.coder_mart_server.user.user_model.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import com.coder_mart_server.user.user_constant.EntityDefaultConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 班级表实体
 */
@Getter
@Setter
@ToString
public class ClassEntity extends BaseEntity implements Serializable {
    //班级id
    private Long classId;

    //班级的管理老师id
    private Long teacherId;

    //班级名称
    private String className;

    //年级
    private Integer grade;

    //学生数量
    private Integer studentNum;

    //最大学生数量
    private Integer maxStudentNum;
}
