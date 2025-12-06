package com.coder_mart_server.security.security_modules.authenticator.filter;

import com.coder_mart_server.public_modules.util.HttpParseUtil;
import com.coder_mart_server.security.security_constant.AuthenticationConstant;
import com.coder_mart_server.security.security_modules.authenticator.annotation.ProviderType;
import com.coder_mart_server.security.security_modules.authenticator.constant.ProviderConstant;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationParam;
import com.coder_mart_server.security.security_modules.authenticator.providers.BaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    //需要拦截的请求（当不满足这个地址时，会跳过这个拦截器）
    public final static AntPathRequestMatcher DEFAULT_ANT_PATH =
            //默认为/login和POST
            new AntPathRequestMatcher(AuthenticationConstant.LOGIN_URL_PATH,AuthenticationConstant.LOGIN_URL_METHOD);

    //各个提供者需要的参数类型map，key为登录类型，value为当前提供者所需参数的类型
    public final static Map<String,Class<AuthenticationParam>> providerParamsMap = new HashMap();

    public AuthenticationFilter(List<AuthenticationProvider> providers,
                                AuthenticationSuccessHandler successHandler,
                                AuthenticationFailureHandler failureHandler){
        super(DEFAULT_ANT_PATH);
        //设置认证提供者（真正的认证交给他）
        this.setAuthenticationManager(new ProviderManager(providers));
        //认证成功后的回调
        this.setAuthenticationSuccessHandler(successHandler);
        //认证失败后的回调
        this.setAuthenticationFailureHandler(failureHandler);
        //初始化提供者需要的参数类型map
        this.initProviderParamsMap(providers);
    }

    /**
     * 初始化各个提供者需要的参数类型map
     * @param providers
     */
    private void initProviderParamsMap(List<AuthenticationProvider> providers){
        for(AuthenticationProvider authenticationProvider : providers){
            BaseProvider provider = (BaseProvider) authenticationProvider;
            //获取当前提供者所需参数的类型
            Class paramTypeClass = provider.getParamTypeClass();
            //获取其注解类型（代表者每个提供者的类型）
            ProviderType providerType = AnnotationUtils.findAnnotation(authenticationProvider.getClass(), ProviderType.class);
            providerParamsMap.put(providerType.type(),paramTypeClass);
        }
    }

    /**
     * 认证入口
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("认证过滤器");
        //登录的类型对应的参数类型
        String providerType = request.getHeader(ProviderConstant.PROVIDER_TYPE);
        Class<AuthenticationParam> paramAuthenticationClass = providerParamsMap.get(providerType);

        //解析请求，并转化为登录类型对应的参数对象
        AuthenticationParam param = HttpParseUtil.parseHttpBodyToObject(request, paramAuthenticationClass);

        //返回认证信息
        return this.getAuthenticationManager().authenticate(param);
    }

    /**
     * 获取当前请求拦截路径
     * @return
     */
    public static AntPathRequestMatcher getDefaultAntPath() {
        return DEFAULT_ANT_PATH;
    }
}
