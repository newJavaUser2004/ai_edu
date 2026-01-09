package com.coder_mart_server;

import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.ClassAdminService;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.StudentAdminService;
import com.coder_mart_server.work_publish_modules.util.UploadUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@SpringBootTest
class CoderMartServerApplicationTests {

//    @Autowired
//    private TokenLiveProperties tokenLiveProperties;
//
//    @Autowired
//    RedisProperties redisProperties;
    @Autowired
    StudentAdminService studentAdminService;
    @Autowired
    ClassAdminService classAdminService;

    @Test
    void contextLoads() throws Exception {
        System.out.println(ISecurity.getSecureUser().getRoles());
//        System.out.println(studentAdminService.getClass());
//        System.out.println(classAdminService.getClass());
//        System.out.println(redisProperties);
//        System.out.println(tokenLiveProperties.getTimeUnit());
//        ExcelReader reader = ExcelUtil.getReader(new File("F:\\临时文件\\网络课程学习进度.xlsx"));
//        ExcelReader reader1 = ExcelUtil.getReader(file.getInputStream());
//        List<List<Object>> read = reader.read();
//        System.out.println(read.get(21526));
//        System.out.println(read.size());
//        List<HashMap> hashMaps = reader.readAll(HashMap.class);
//        System.out.println(hashMaps.get(0).get("学号").getClass());
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("userId","123");
//        stringObjectHashMap.put("role","老师");
//        stringObjectHashMap.put("lastUpdateTime","100");
//        SecureUser secureUser = ReflectionUtil.buildObjectByClass(SecureUser.class, stringObjectHashMap);
//        System.out.println(secureUser);
        //编写一个excel读工具，
        // 1.读取所有excel内容为List
        // 2.获取需要转化的对象的属性注解，解析注解中的字段名，与hashMap的key进行对应
        // 3.根据对应的value
    }
}
