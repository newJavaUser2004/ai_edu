package com.coder_mart_server.security.security_modules.authorizer.filter;

import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

public class SecurityAuthorizationFilter extends AuthorizationFilter {


    public SecurityAuthorizationFilter(AuthorizationManager<HttpServletRequest> authorizationManager) {
        super(authorizationManager);
    }

    /**
     * 从安全上下文中获取认证对象
     * @return
     */
    @Override
    public AuthorizationManager<HttpServletRequest> getAuthorizationManager() {
        return super.getAuthorizationManager();
    }
}
