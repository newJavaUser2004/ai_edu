package com.coder_mart_server.security.security_modules.context_persistence.filter;

import com.coder_mart_server.security.security_constant.AuthenticationConstant;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurityContext;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component 避免被注册到servlet物理链中
@RequiredArgsConstructor
@Slf4j
public class SecurityContextRecoverFilter extends SecurityContextPersistenceFilter {

    private final SecurityContextRepository contextRepository;

    //重写上下文持久化逻辑

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("上下文过滤器");
        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);

        //载入上下文
        ISecurityContext securityContext = (ISecurityContext)contextRepository.loadContext(holder);
        ISecurityContextHolder.setSecurityContext(securityContext);

        //放行到下一个过滤器
        chain.doFilter(holder.getRequest(), holder.getResponse());

        //当不是测试请求时，则进行刷新认证操作
        if (!request.getMethod().equals(AuthenticationConstant.OPTIONS_URL_METHOD)) {
            //刷新认证信息
            contextRepository.saveContext(ISecurityContextHolder.getContext(),
                    holder.getRequest(), holder.getResponse()
            );
            //清除上下文
            ISecurityContextHolder.clearContext();
        }
    }
}















