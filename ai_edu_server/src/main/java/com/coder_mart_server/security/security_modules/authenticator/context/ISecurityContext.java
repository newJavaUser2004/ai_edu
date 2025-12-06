package com.coder_mart_server.security.security_modules.authenticator.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public class ISecurityContext implements SecurityContext {

    //安全认证的上下文内容
    private final static ThreadLocal<Authentication> SECURITY_AUTHENTICATION = new TransmittableThreadLocal();

    //单例模式
    private static ISecurityContext securityContext;

    //单例模式
    public static ISecurityContext newOneContextObj()

    {
        if(securityContext != null){
            return securityContext;
        }
        securityContext = new ISecurityContext();
        return securityContext;
    }

    //设置认证信息
    @Override
    public void setAuthentication(Authentication context){
        SECURITY_AUTHENTICATION.set(context);
    }

    //获取认证信息
    @Override
    public Authentication getAuthentication(){
        return SECURITY_AUTHENTICATION.get();
    }

    //删除认证信息
    public void clearAuthentication(){
        SECURITY_AUTHENTICATION.remove();
    }
}
