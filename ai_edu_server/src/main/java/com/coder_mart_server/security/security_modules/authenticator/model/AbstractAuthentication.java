package com.coder_mart_server.security.security_modules.authenticator.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;


/**
 * Authentication的抽象类，负责实现其方法，让其它实现类更加简洁，不需要重复实现
 */
public abstract class AbstractAuthentication implements Authentication {

    private boolean authenticated = false;

    /**
     * 判断是否认证
     * @return
     */
    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    /**
     * 设置是否认证
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
