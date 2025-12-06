package com.coder_mart_server.user.user_modules.user_teacher.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * t_role表对应mapper
 */
@Mapper
public interface RoleMapper {

    /**
     * 从用户id中获取学生用户
     * @param userIdList
     * @return
     */
    List<Long> selectStudentFromUserIdList(@Param("userIdList") List<Long> userIdList);
}
