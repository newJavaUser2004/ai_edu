package com.coder_mart_server.work_publish_modules.students.pojo.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 学生上传的答案表
 * t_stuAnswer
 */
@Data
public class StuAnswer extends BaseEntity {

    //学生ID
    private Long studentId;

    //学生作业的url
    private List<String> url;


}
