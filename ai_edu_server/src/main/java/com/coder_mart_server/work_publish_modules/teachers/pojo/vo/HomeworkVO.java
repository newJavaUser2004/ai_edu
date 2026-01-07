package com.coder_mart_server.work_publish_modules.teachers.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 上传的作业返回表
 */
@Data
public class HomeworkVO {

    private Integer workType;

    private String url;

    private String workName;

    private LocalDateTime deadline;

    private Long homeworkId;


}
