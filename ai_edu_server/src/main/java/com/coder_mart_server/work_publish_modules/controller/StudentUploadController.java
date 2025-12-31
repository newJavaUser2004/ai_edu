package com.coder_mart_server.work_publish_modules.controller;

import com.coder_mart_server.public_modules.model.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resource-student")
@Slf4j
public class StudentUploadController {

    //学生上传答案
    @PostMapping("/upload/homework")
    public Result uploadMyHomework(){


        return Result.success();
    }
}
