package com.coder_mart_server.user.user_modules.user_teacher.mappers;

import com.coder_mart_server.user.user_model.entity.ClassRosterEntity;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.vo.ClassRosterVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface ClassRosterMapper {
    /**
     * 根据班级id查找学生信息集合
     * @param classId
     * @return
     */
    ClassRosterVO findClassRostByClassId(@Param("classId") Long classId);

    /**
     * 批量拉学生进入班级
     * @param classRosterEntities
     */
    Integer insertStudentToClassRoster(@Param("classRosterEntities") List<ClassRosterEntity> classRosterEntities);

    /**
     * 逻辑删除对应班级花名册
     * @param classIdList
     */
    void deleteClassRoster(@Param("classIdList") List<Long> classIdList);
}
