package com.coder_mart_server.security.security_modules.authenticator.handler;

import com.coder_mart_server.public_modules.handlers.HttpResponseHandler;
import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功
 */
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 认证成功的回调
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取认证信息
        AuthenticationResult authenticationResult = (AuthenticationResult) authentication;
        Result<AuthenticationResult> successResult = Result.success(authenticationResult);
        //返回认证信息响应
        HttpResponseHandler.successResponse(response,successResult);
    }
}
