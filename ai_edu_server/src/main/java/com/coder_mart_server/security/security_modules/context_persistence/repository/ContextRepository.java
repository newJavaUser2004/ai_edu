package com.coder_mart_server.security.security_modules.context_persistence.repository;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.exceptions.base.BaseException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.util.HttpParseUtil;
import com.coder_mart_server.security.security_constant.AuthenticationConstant;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurityContext;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_modules.context_persistence.constant.HttpHeadConstant;
import com.coder_mart_server.security.security_properties.TokenLiveProperties;
import com.coder_mart_server.security.security_util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContextRepository implements SecurityContextRepository {

    private final TokenLiveProperties tokenLiveProperties;

    //载入上下文
    @SneakyThrows
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        //创建上下文对象
        ISecurityContext securityContext = ISecurityContext.newOneContextObj();

        HttpServletRequest request = requestResponseHolder.getRequest();

        //若是登录请求，或者资源请求，则创建一个新对象
        if(isLoginRequest(request) || isResource(request)){
            securityContext.setAuthentication(new AuthenticationResult());
            return securityContext;
        }

        //解析token请求头
        String token = HttpParseUtil.parseHttpHead(request, HttpHeadConstant.Http_TOKEN_HEAD);

        //查询redis，获取认证信息
        AuthenticationResult authenticationResult = AuthenticationUtil.getAuthenticationInfoByToken(token);

        //不为空，证明登录过，且认证信息未过期
        if(authenticationResult != null){
            securityContext.setAuthentication(authenticationResult);
            return securityContext;
        }

        //token过期或者未登录过，抛出异常不放行
        throw new UserException(ResultEnum.TOKEN_OVER_TIME_ERROR);
    }

    //将上下文持久化
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        //将认证信息保存到redis，对数据进行刷新
        ISecurityContext securityContext = (ISecurityContext) context;
        if(securityContext != null){
            AuthenticationResult authentication = (AuthenticationResult) securityContext.getAuthentication();
            System.out.println(authentication);
            //更新数据（如果长期存储则跳过）
            AuthenticationUtil.updateAuthenticationInfo(
                    authentication.getToken(),
                    authentication,
                    tokenLiveProperties
            );
        }
    }

    //是否是登录的请求
    public boolean isLoginRequest(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        //如果登录路径是拦截的登录路径，同时请求方式也与拦截要求一致，或者是OPTIONS请求，则也放行
        if((AuthenticationConstant.LOGIN_URL_PATH.equals(requestURI)
                && AuthenticationConstant.LOGIN_URL_METHOD.equals(method))
                || AuthenticationConstant.OPTIONS_URL_METHOD.equals(method)){
            return true;
        }
        return false;
    }

    //是否是资源
    public boolean isResource(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        //如果登录路径是拦截的登录路径，同时请求方式也与拦截要求一致，或者是OPTIONS请求，则也放行
        return requestURI.contains("/files");
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}













