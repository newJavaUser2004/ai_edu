package com.coder_mart_server.security.security_modules.authenticator.context;

import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_model.entity.SecureUser;

public class ISecurity {

    /**
     * 获取存储的信息
     * @return
     */
    public static SecureUser getSecureUser(){
        AuthenticationResult authenticationResult = (AuthenticationResult)ISecurityContextHolder.getContextAuthentication();
        return authenticationResult.getUserInfo();
    }

    /**
     * 获取token
     * @return
     */
    public static String getToken(){
        AuthenticationResult authenticationResult = (AuthenticationResult)ISecurityContextHolder.getContextAuthentication();
        return authenticationResult.getToken();
    }
}
