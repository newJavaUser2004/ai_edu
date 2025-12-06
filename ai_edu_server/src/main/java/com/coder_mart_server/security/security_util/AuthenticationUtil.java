package com.coder_mart_server.security.security_util;

import com.coder_mart_server.security.security_constant.SecurityRedisKeyConstant;
import com.coder_mart_server.public_modules.util.RedisUtil;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_properties.TokenLiveProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 认证工具
 */
public class AuthenticationUtil {

    /**
     * 设置认证信息
     * @param token
     * @param authenticationResult
     * @param tokenLiveProperties
     */
    public final static void setAuthenticationInfo(String token, AuthenticationResult authenticationResult, TokenLiveProperties tokenLiveProperties){
        String tokenRedisKey = RedisUtil.buildKey(SecurityRedisKeyConstant.USER_AUTHENTICATION_INFO_KEY, token);

        //如果长期存储，则不设置过期时间
        if(tokenLiveProperties.isLongLive()){
            RedisUtil.RedisString.addObjectToString(
                    tokenRedisKey,
                    authenticationResult
            );
            return;
        }

        //不长期存储，则设置过期时间
        RedisUtil.RedisString.addObjectToString(
                tokenRedisKey,
                authenticationResult,
                tokenLiveProperties.getLiveTime(),
                tokenLiveProperties.getTimeUnit());
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     */
    public final static AuthenticationResult getAuthenticationInfoByToken(String token) throws JsonProcessingException {
        //查询redis，获取认证信息
        AuthenticationResult authenticationResult = RedisUtil.RedisString.getObjectInString(
                RedisUtil.buildKey(SecurityRedisKeyConstant.USER_AUTHENTICATION_INFO_KEY, token),
                AuthenticationResult.class
        );
        return authenticationResult;
    }

    /**
     * 更新认证信息
     * @param token
     * @param authenticationResult
     * @param tokenLiveProperties
     */
    public final static void updateAuthenticationInfo(
            String token,
            AuthenticationResult authenticationResult,
            TokenLiveProperties tokenLiveProperties
    ){
        if(tokenLiveProperties.isLongLive()){
            return;
        }
        //添加认证信息
        RedisUtil.RedisString.addObjectToString(
                RedisUtil.buildKey(SecurityRedisKeyConstant.USER_AUTHENTICATION_INFO_KEY, token),
                authenticationResult,
                tokenLiveProperties.getLiveTime(),
                tokenLiveProperties.getTimeUnit()
        );
    }
}


















