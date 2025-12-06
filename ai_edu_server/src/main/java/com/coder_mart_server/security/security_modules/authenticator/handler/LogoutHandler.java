package com.coder_mart_server.security.security_modules.authenticator.handler;

import com.coder_mart_server.security.security_constant.AuthenticationConstant;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录
 */
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    private final static AntPathRequestMatcher DEFAULT_LOGOUT_PATH = new AntPathRequestMatcher(
            AuthenticationConstant.LOGOUT_URL_PATH,
            AuthenticationConstant.LOGOUT_URL_METHOD
            );

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }

    public static AntPathRequestMatcher getDefaultLogoutPath(){
        return DEFAULT_LOGOUT_PATH;
    }
}
