package com.coder_mart_server.work_publish_modules.teachers.mappers;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassTeacherMapper {

    boolean permission(Long userId,Long classId);
}
