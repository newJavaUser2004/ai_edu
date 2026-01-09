package com.coder_mart_server.security.security_modules.excption_handle.filter;

import com.coder_mart_server.public_modules.exceptions.base.BaseException;
import com.coder_mart_server.public_modules.handlers.HttpResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常捕获过滤器(能够捕获所有异常，包括过滤器和controller的异常)
 */
@Slf4j
public class ExceptionCatchFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("异常捕获过滤器");
            filterChain.doFilter(request,response);
        }
        //捕获自定义异常
        catch (BaseException baseException){
            HttpResponseHandler.errorResponse(response, baseException.getResultEnum());
        }
        //捕获运行时异常
        catch (RuntimeException runtimeException){
            HttpResponseHandler.errorResponse(response, runtimeException.getMessage());
            runtimeException.printStackTrace();
        }
    }
}
