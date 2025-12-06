package com.coder_mart_server.security.security_modules.authenticator.providers;

import com.coder_mart_server.security.security_modules.authenticator.context.ISecurityContextHolder;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 基类认证提供者
 * 根据所需参数，选择对应的认证提供者进行认证
 * @param <T>
 */
public interface BaseProvider<T> extends AuthenticationProvider {

    //获取所需参数类型
    Class<T> getParamTypeClass();

    //获取认证结果
    Authentication authenticateResult(T authentication);

    @Override
    default Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取认证结果
        AuthenticationResult authenticateResult = (AuthenticationResult)authenticateResult((T) authentication);

        //存储认证结果
        ISecurityContextHolder.setContextAuthentication(authenticateResult);

        return authenticateResult;
    }

    //筛选是否使用当前Provider
    default boolean supports(Class<?> authentication){
        //判断传入的参数，是不是与当前所需参数的运行类型相同
        return authentication.equals(getParamTypeClass());
    }
}
