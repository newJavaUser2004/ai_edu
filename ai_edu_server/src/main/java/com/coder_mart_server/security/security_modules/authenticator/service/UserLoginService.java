package com.coder_mart_server.security.security_modules.authenticator.service;

import com.coder_mart_server.security.security_model.entity.SecureUser;
import com.coder_mart_server.security.security_modules.authenticator.model.UsernamePasswordAuthentication;

/**
 * 用户登录逻辑
 */
public interface UserLoginService {

    /**
     * 通过用户名和密码进行登录
     * @param authentication
     * @return
     */
    SecureUser userLoginByUsername(UsernamePasswordAuthentication authentication);
}
