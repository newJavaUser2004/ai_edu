package com.coder_mart_server.work_publish_modules.students.pojo.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SelfHomeworkDTO {

    private MultipartFile multipartFile;

    private Long studentId;

    private String content;

    private Long homeworkId;

    private Long classId;
}
