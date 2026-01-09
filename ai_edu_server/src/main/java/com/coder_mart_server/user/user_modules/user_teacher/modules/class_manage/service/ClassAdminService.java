package com.coder_mart_server.user.user_modules.user_teacher.modules.class_manage.service;

import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoChangeDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassInfoVO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;

import java.util.List;

/**
 * 班级管理业务
 */
public interface ClassAdminService {

    /**
     * 根据老师id，批量获取班级信息
     * @param teacherId
     * @return
     */
    List<ClassInfoVO> findClassInfoList(Long teacherId);

    /**
     * 根据班级id，查询班级花名册
     * @param classId
     * @return
     */
    ClassRosterVO findClassRoster(Long classId);

    /**
     * 添加班级
     * @param classInfoDTOS
     */
    void addClassInfo(ClassInfoDTO classInfoDTOS);

    /**
     * 逻辑删除班级id
     * @param classIdList
     */
    void deleteClassInfo(List<Long> classIdList);

    /**
     * 修改班级信息
     * @param classInfoChangeDTO
     */
    void changeClassInfo(ClassInfoChangeDTO classInfoChangeDTO);
}
