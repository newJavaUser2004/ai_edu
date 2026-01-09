package com.coder_mart_server.work_publish_modules.controller;


import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import com.coder_mart_server.security.security_modules.authorizer.constant.PermissionsConstant;
import com.coder_mart_server.work_publish_modules.service.UploadService;

import com.coder_mart_server.work_publish_modules.students.pojo.vo.AnswerVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.*;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    /**
     * 老师上传资源，将url添加进资源表中
     * @return
     */

    @PermissionsType(types = {PermissionsConstant.TEACHER_ROLES,PermissionsConstant.HEAD_TEACHER})
    @PostMapping(value = "/upload/files",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadResources(@ModelAttribute UploadResourceDTO uploadResourceDTO,
                                  @RequestParam Long classId){

        uploadService.uploadResources(uploadResourceDTO,classId);

        return Result.success();
    }

    /**
     * 上传资源回显
     * @return
     */
    @GetMapping("/upload/{classId}")
    public Result<List<ResourceVO>> selectVO(@PathVariable("classId") Long classId){

        List<ResourceVO> resourceVO = uploadService.research(classId);

        return Result.success(resourceVO);
    }

    /**
     * 老师批量逻辑删除资源
     * @param logicDeleteDTO
     * @return
     */
    @PutMapping("/upload/delete")
    @PermissionsType(types = {PermissionsConstant.TEACHER_ROLES,PermissionsConstant.HEAD_TEACHER})
    public Result deleteResource(LogicDeleteDTO logicDeleteDTO){

        uploadService.deleteResource(logicDeleteDTO);

        return Result.success();
    }

    /**
     * 老师上传作业
     * @param classId
     * @return
     */
    @PermissionsType(types = {PermissionsConstant.TEACHER_ROLES,PermissionsConstant.HEAD_TEACHER})
    @PostMapping(value = "/upload/homeworks",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadWork(@ModelAttribute UploadHomeworkDTO uploadHomeworkDTO,
                             @RequestParam("classId") Long classId){

        uploadService.uploadHomework(uploadHomeworkDTO,classId);

        return Result.success();
    }

    /**
     * 查看老师发布的所有作业
     * @param classId
     * @return
     */
    @GetMapping("/{classId}/query/homeworks")
    public Result<List<HomeworkVO>> queryHomework(@PathVariable Long classId){

        List<HomeworkVO> homeworkVOList = uploadService.queryHomework(classId);

        return Result.success(homeworkVOList);
    }

    /**
     * 老师查看所有学生作业
     * @return
     */
    @PermissionsType(types = {PermissionsConstant.TEACHER_ROLES,PermissionsConstant.HEAD_TEACHER})
    @GetMapping("/resource/{classId}/query/{homeworkId}/answers")
    public Result<List<AnswerVO>> queryAllAnswer(@PathVariable Long classId,
                                           @PathVariable Long homeworkId){
       List<AnswerVO> answerVOList = uploadService.queryAllAnswer(classId,homeworkId);

       return Result.success(answerVOList);
    }
}
