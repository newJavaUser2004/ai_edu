package com.coder_mart_server.user.user_modules.user_teacher.mappers;

import com.coder_mart_server.user.user_model.entity.ClassEntity;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.bo.ClassInfoChangeBO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoChangeDTO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassInfoVO;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * class表mapper
 */
@Mapper
public interface ClassMapper {

    /**
     * 通过老师id查找班级信息集合
     * @param teacherId
     * @return
     */
    List<ClassInfoVO> findClassInfoListByTeacherId(@Param("teacherId") Long teacherId);

    /**
     * 插入班级信息
     * @param classEntities
     */
    void insertClassInfo(@Param("classEntities") List<ClassEntity> classEntities);

    /**
     * 逻辑删除对应班级
     * @param classIdList
     */
    void deleteClassInfo(@Param("classIdList") List<Long> classIdList);

    /**
     * 修改班级信息
     * @param classInfoChangeBO
     */
    void changeClassInfo(@Param("classInfoChangeBO") ClassInfoChangeBO classInfoChangeBO);

    /**
     * 通过classId查找班级信息
     * @param classId
     * @return
     */
    ClassEntity selectClassByClassId(@Param("classId") Long classId);

    /**
     * 更新班级新的学生人数
     * @param classId
     * @param addStudentNum
     */
    void updateClassStudentNum(@Param("classId") Long classId,@Param("addStudentNum") Integer addStudentNum);

    /**
     * 批量删除学生
     */

}
