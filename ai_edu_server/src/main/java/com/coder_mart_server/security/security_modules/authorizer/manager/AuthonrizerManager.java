package com.coder_mart_server.security.security_modules.authorizer.manager;

import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_modules.authorizer.annotation.PermissionsType;
import com.coder_mart_server.security.security_model.entity.SecureUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthonrizerManager implements AuthorizationManager<HttpServletRequest> {

    /**
     * 判断是否具有访问权限
     * @param authenticationSupplier
     * @return
     */
    @SneakyThrows
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, HttpServletRequest request) {
        log.info("权限过滤器");
        return new AuthorizationDecision(true);
    }
}












