package com.coder_mart_server.work_publish_modules.teachers.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ResourceDTO {

    private MultipartFile multipartFile;

    private String resourceName;
}
