package com.coder_mart_server.work_publish_modules.students.pojo.vo;


import lombok.Data;

import java.util.List;

@Data
public class AnswerVO {

    private Long homeworkId;

    private Long studentId;

    private Long classId;

    private List<String> url;

    private String content;

}
