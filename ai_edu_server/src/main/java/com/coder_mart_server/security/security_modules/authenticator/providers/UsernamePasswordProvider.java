package com.coder_mart_server.security.security_modules.authenticator.providers;

import com.coder_mart_server.public_modules.helppers.UniqueIdHelper;
import com.coder_mart_server.public_modules.properties.SnowProperties;
import com.coder_mart_server.security.security_model.entity.SecureUser;
import com.coder_mart_server.security.security_modules.authenticator.annotation.ProviderType;
import com.coder_mart_server.security.security_modules.authenticator.constant.ProviderConstant;
import com.coder_mart_server.security.security_modules.authenticator.model.AuthenticationResult;
import com.coder_mart_server.security.security_modules.authenticator.model.UsernamePasswordAuthentication;
import com.coder_mart_server.security.security_modules.authenticator.service.UserLoginService;
import com.coder_mart_server.security.security_properties.TokenLiveProperties;
import com.coder_mart_server.security.security_util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 单点登录器
 */
@Component
@ProviderType(type = ProviderConstant.PASSWORD_TYPE)
@RequiredArgsConstructor
@Slf4j
public class UsernamePasswordProvider implements BaseProvider<UsernamePasswordAuthentication>{

    //所需参数的类型
    Class<UsernamePasswordAuthentication> paramTypeClass = UsernamePasswordAuthentication.class;

    //登录
    private final UserLoginService userLoginService;

    //token参数
    private final TokenLiveProperties tokenLiveProperties;

    private final UniqueIdHelper uniqueIdHelper;

    //获取所需类型
    @Override
    public Class<UsernamePasswordAuthentication> getParamTypeClass() {
        return this.paramTypeClass;
    }

    /**
     * 返回认证结果
     * @param authentication
     * @return
     */
    @Override
    public Authentication authenticateResult(UsernamePasswordAuthentication authentication) {
        //查询数据库，通过用户名与密码获取用户信息
        SecureUser secureUser = userLoginService.userLoginByUsername(authentication);
        secureUser.setLastUpdateTime(System.currentTimeMillis());

        //todo 生成token
        String token = uniqueIdHelper.snowIdBuild().toString();

        //创建Authentication对象
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.setToken(token);
        authenticationResult.setUserInfo(secureUser);

        //token为key，将用户信息存入redis
        AuthenticationUtil.setAuthenticationInfo(token,authenticationResult,tokenLiveProperties);

        return authenticationResult;
    }
}
