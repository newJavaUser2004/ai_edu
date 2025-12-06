package com.coder_mart_server.security.security_modules.authenticator.mappers;

import com.coder_mart_server.security.security_model.entity.SecureUser;
import com.coder_mart_server.security.security_modules.authenticator.model.UsernamePasswordAuthentication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginMapper {

    //通过认证信息查询用户
    SecureUser selectUserByAuthentication(@Param("authentication") UsernamePasswordAuthentication authentication);

}
