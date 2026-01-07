package com.coder_mart_server.work_publish_modules.controller;

import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import com.coder_mart_server.security.security_modules.authorizer.constant.PermissionsConstant;
import com.coder_mart_server.work_publish_modules.service.UploadService;
import com.coder_mart_server.work_publish_modules.students.pojo.dto.UploadSelfHomeworkDTO;
import com.coder_mart_server.work_publish_modules.students.pojo.vo.AnswerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resource-student")
@Slf4j
public class StudentUploadController {

    private final UploadService uploadService;


    //学生上传答案
    @PostMapping("/homeworks/answers")
    @PermissionsType(types = {PermissionsConstant.STUDENT_ROLES})
    public Result uploadMyHomework(UploadSelfHomeworkDTO uploadSelfHomeworkDTO){

        uploadService.uploadMyAnswer(uploadSelfHomeworkDTO);
        return Result.success();
    }


}
