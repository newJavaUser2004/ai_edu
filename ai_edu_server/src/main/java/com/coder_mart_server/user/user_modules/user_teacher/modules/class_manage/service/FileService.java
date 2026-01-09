package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理器
 */
public interface FileService {

    //上传图片文件
    String uploadImageFile(MultipartFile multipartFile);
}
