package com.coder_mart_server.security.security_config;

import com.coder_mart_server.security.security_modules.authenticator.filter.AuthenticationFilter;
import com.coder_mart_server.security.security_modules.authenticator.handler.LogoutHandler;
import com.coder_mart_server.security.security_modules.authorizer.filter.SecurityAuthorizationFilter;
import com.coder_mart_server.security.security_modules.context_persistence.filter.SecurityContextRecoverFilter;
import com.coder_mart_server.security.security_modules.excption_handle.filter.ExceptionCatchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Order(100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //cors配置对象
    private final CorsConfigurationSource corsConfigurationSource;

    //权限过滤器参数
    private final AuthorizationManager<HttpServletRequest> authorizationManager;

    //上下文过滤器参数
    private final SecurityContextRepository contextRepository;

    //认证过滤器参数
    private final List< AuthenticationProvider > providers;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //异常捕获过滤器
                .addFilterBefore(new ExceptionCatchFilter(), SecurityContextPersistenceFilter.class)

                //上下文过滤器
                .securityContext().disable()
                .sessionManagement().disable()
                .addFilterAt(new SecurityContextRecoverFilter(contextRepository), SecurityContextPersistenceFilter.class)

                //权限过滤器
                .addFilterAfter(new SecurityAuthorizationFilter(authorizationManager), UsernamePasswordAuthenticationFilter.class)

                //认证过滤器
                .formLogin().disable()
                .addFilterAt(
                        new AuthenticationFilter(providers,successHandler,failureHandler),
                        UsernamePasswordAuthenticationFilter.class
                )

                //添加退出登录处理器
                .logout((logout) -> {
                    logout
                            //设置退出登录的路径与处理器
                            .logoutRequestMatcher(LogoutHandler.getDefaultLogoutPath())
                            .addLogoutHandler(new LogoutHandler());
                })

                .authorizeHttpRequests()
                    //设置除认证、退出登录接口可匿名，其它请求需要认证
                    .requestMatchers(new AntPathRequestMatcher("/**","OPTIONS")).permitAll()
                    .requestMatchers(AuthenticationFilter.getDefaultAntPath()).permitAll()
                    .requestMatchers(LogoutHandler.getDefaultLogoutPath()).permitAll()
//                    .anyRequest().authenticated()

                .and()
                .csrf().disable()
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource);});
    }
}
