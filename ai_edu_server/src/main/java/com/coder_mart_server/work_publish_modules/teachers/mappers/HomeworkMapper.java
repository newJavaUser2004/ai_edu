package com.coder_mart_server.work_publish_modules.teachers.mappers;


import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.Homework;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeworkMapper {
    /**
     * 上传作业
     * @param homeworkPO
     */
    void uploadHomework(@Param("poList") List<Homework> homeworkPO);

    List<HomeworkVO> queryHomework(@Param("classId") Long classId);

    void deleteHomework();



}
