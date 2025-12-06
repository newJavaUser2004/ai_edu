package com.coder_mart_server.security.security_modules.authorizer.interceptors;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.security.security_model.entity.SecureUser;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurityContextHolder;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
@Slf4j
public class SecurityAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("权限拦截器");
        //获取当前请求对应的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        SecureUser secureUser = ISecurity.getSecureUser();

        //判断是否有访问权限
        if(hasPermissions(handlerMethod,secureUser)){
            log.info("权限认证通过");
            return true;
        }

        log.error("权限认证不通过");
        throw new UserException(ResultEnum.ROLE_ERROR);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 有无访问权限
     * @param handlerMethod
     * @return
     */
    public boolean hasPermissions(HandlerMethod handlerMethod, SecureUser userInfo){
        PermissionsType methodAnnotation;

        Map<String,Boolean> permissionsMap = new HashMap<>();

        //若当前方法上并无权限注解，则直接放行
        if((methodAnnotation = handlerMethod.getMethodAnnotation(PermissionsType.class)) == null){
            return true;
        }

        //进行鉴别，如果当前用户的权限允许，则放行
        String[] permissionsTypes = methodAnnotation.types();
        for(String permissionsType : permissionsTypes){
            permissionsMap.put(permissionsType,true);
        }

        List<String> roles = userInfo.getRoles();
        for(String userRole : roles) {
            if(permissionsMap.containsKey(userRole)){
                return true;
            }
        }

        return false;
    }
}
