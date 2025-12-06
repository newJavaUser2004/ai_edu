package com.coder_mart_server.security.security_modules.authenticator.context;

import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Setter
public class ISecurityContextHolder {

    private static ISecurityContext iSecurityContext;

    /**
     * 获取原本上下文
     * @return
     */
    public static SecurityContext getCorsContext(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

    /**
     * 设置原本上下文认证对象
     */
    public static void setCorsContextAuthentication(Authentication authentication){
        SecurityContext corsContext = getCorsContext();
        corsContext.setAuthentication(authentication);
    }

    /**
     * 设置原本上下文
     */
    public static void setCorsContext(SecurityContext context){
        SecurityContextHolder.setContext(context);
    }

    /**
     * 清空原本上下文
     */
    public static void clearCorsContext(){
        SecurityContextHolder.clearContext();
    }


    /**
     * 设置上下文
     * @param securityContext
     */
    public static void setSecurityContext(ISecurityContext securityContext){
        setCorsContext(securityContext);
        iSecurityContext = securityContext;
    }

    /**
     * 设置上下文中的认证对象
     * @param authentication
     */
    public static void setContextAuthentication(Authentication authentication){
        setCorsContextAuthentication(authentication);
        iSecurityContext.setAuthentication(authentication);
    }

    /**
     * 获取上下文对象
     * @return
     */
    public static SecurityContext getContext(){
        return iSecurityContext;
    }

    /**
     * 获取上下文中的认证对象
     * @return
     */
    public static Authentication getContextAuthentication(){
        Authentication authentication = iSecurityContext.getAuthentication();
        return authentication;
    }

    /**
     * 清空上下文，防止数据污染
     */
    public static void clearContext(){
        clearCorsContext();
        if(iSecurityContext != null) {
            iSecurityContext.clearAuthentication();
        }
    }

}


















