package com.coder_mart_server.work_publish_modules.util;

import com.coder_mart_server.public_modules.constant.ResponseConstant;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 上传文件的工具类
 */

public class UploadUtil {



    public static String upload(MultipartFile file){

        if (file.isEmpty()){
            return ResponseConstant.ErrorMessage.UPLOAD_FILE_IS_EMPTY;
        }

        //获取文件名后缀
        String originName = file.getOriginalFilename();
        int dot = originName.lastIndexOf(".");
        if (dot == -1){return ResponseConstant.ErrorMessage.UPLOAD_FILE_HAS_NO_EXTENSION;}
        String tail = originName.substring(dot);

        String newFilename = UUID.randomUUID()+tail;

        //保存文件到本地
        Path rootDir = Paths.get("D:/keshe_of_LocalFiles");
        //文件的父目录
        Path realDir = rootDir.resolve(LocalDate.now().toString());
        //文件路径
        Path realFile = realDir.resolve(newFilename);

        try {

            //创建目录
            Files.createDirectories(realDir);
            //写盘
            file.transferTo(realFile);

        } catch (IOException e) {
            return "上传失败"+e.getMessage();
        }

        String relativePath = LocalDate.now() +"/"+ newFilename;
        String url = "http://localhost:8080/files/" + relativePath;

        return url;
    }

}
