package com.coder_mart_server.work_publish_modules.teachers.pojo.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 作业表实体
 * t_homework
 */
@Data
public class Homework extends BaseEntity implements Serializable {

    //作业表主键
    private Long id;

    //作业的业务逻辑id
    private Long homeworkId;

    //截止时间
    private LocalDateTime deadline;

    //作业名称
    private String workName;

    //作业url
    private String url;

    //作业类型 0:随堂作业/1:课后作业
    private  Integer workType;

    //班级id
    private Long classId;


}
