package com.coder_mart_server.security.security_modules.authenticator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 用户单点登录的参数
 */
@Setter
@Getter
public class UsernamePasswordAuthentication extends AuthenticationParam {
    //账户
    private String username;

    //密码
    private String password;

    //客户端类型（老师or学生）
    private Integer roleType;
}
