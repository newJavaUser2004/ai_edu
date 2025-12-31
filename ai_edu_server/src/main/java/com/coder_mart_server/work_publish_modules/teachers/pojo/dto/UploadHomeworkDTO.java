package com.coder_mart_server.work_publish_modules.teachers.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 上传作业的DTO
 */
@Getter
@Setter
@ToString
public class UploadHomeworkDTO {

    //截止时间
    private LocalDateTime deadline;

    //作业类型
    private Integer workType;

    //作业名称
    private String workName;
}
