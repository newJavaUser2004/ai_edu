package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.controller;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import com.coder_mart_server.security.security_modules.authorizer.constant.PermissionsConstant;
import com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service.ClassAdminService;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoChangeDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassInfoVO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 班级管理控制器
 * (查看班级信息、查看班级花名册、新增班级、删除班级、修改班级信息)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/class/admin")
@Slf4j
public class ClassAdminController {

    //班级管理业务
    private final ClassAdminService classAdminService;

    /**
     * 查看老师对应班级的所有信息，{权限为班主任}
     * @param
     * @return
     */
    @GetMapping("/find/info")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result<List<ClassInfoVO>> findClassInfoList(){
        log.info("查看老师:{}对应班级的所有信息", ISecurity.getSecureUser().getUserId());
        Long teacherId = ISecurity.getSecureUser().getUserId();
        List<ClassInfoVO> classInfoList = classAdminService.findClassInfoList(teacherId);
        return Result.success(classInfoList);
    }

    /**
     * 查看班级花名册，{权限为老师、班主任}
     * @param classId
     * @return
     */
    @GetMapping("/find/roster/{classId}")
    @PermissionsType(types = {PermissionsConstant.TEACHER_ROLES,PermissionsConstant.HEAD_TEACHER})
    public Result<ClassRosterVO> findClassRoster(@PathVariable("classId") Long classId){
        log.info("查看老师:{}班级花名册",ISecurity.getSecureUser().getUserId());
        ClassRosterVO classRoster = classAdminService.findClassRoster(classId);
        return Result.success(classRoster);
    }

    /**
     * 新增班级，{权限为班主任}
     * @param
     * @return
     */
    @PostMapping("/add/info")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result addClassInfo(@RequestParam("file") MultipartFile file,
                               @RequestParam("className") String className,
                               @RequestParam("grade") Integer grade,
                               @RequestParam("maxStudentNum") Integer maxStudentNum){
        log.info("新增老师:{}班级",ISecurity.getSecureUser().getUserId());

        ClassInfoDTO classInfoDTO = new ClassInfoDTO();
        classInfoDTO.setFile(file);
        classInfoDTO.setClassName(className);
        classInfoDTO.setGrade(grade);
        classInfoDTO.setMaxStudentNum(grade);

        classAdminService.addClassInfo(classInfoDTO);
        return Result.success();
    }

    /**
     * 删除班级，{权限为班主任}
     * @param classIdList
     * @return
     */
    @DeleteMapping("/delete/info")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result deleteClassInfo(@RequestBody List<Long> classIdList){
        log.info("删除老师:{}班级",ISecurity.getSecureUser().getUserId());
        if(classIdList == null || classIdList.isEmpty()){
            throw new UserException(ResultEnum.PARAM_ERROR);
        }
        classAdminService.deleteClassInfo(classIdList);
        return Result.success();
    }

    /**
     * 修改班级信息，{权限为班主任}
     * @param classInfoChangeDTO
     * @return
     */
    @PutMapping("/change/info")
    @PermissionsType(types = {PermissionsConstant.HEAD_TEACHER})
    public Result changeClassInfo(@RequestBody ClassInfoChangeDTO classInfoChangeDTO){
        log.info("修改老师:{}班级信息",ISecurity.getSecureUser().getUserId());
        classAdminService.changeClassInfo(classInfoChangeDTO);
        return Result.success();
    }

    //todo 根据班级名模糊查询班级信息
}
