package com.coder_mart_server.security.security_modules.authenticator.service.impl;

import com.coder_mart_server.public_modules.exceptions.UserException;
import com.coder_mart_server.public_modules.handlers.HttpResponseHandler;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.model.enums.StringEnum;
import com.coder_mart_server.security.security_model.entity.SecureUser;
import com.coder_mart_server.security.security_modules.authenticator.mappers.UserLoginMapper;
import com.coder_mart_server.security.security_modules.authenticator.model.UsernamePasswordAuthentication;
import com.coder_mart_server.security.security_modules.authenticator.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginMapper userLoginMapper;

    @Override
    public SecureUser userLoginByUsername(UsernamePasswordAuthentication authentication) {
        if(authentication == null){
            //抛出登录信息不完整异常
            throw new UserException(ResultEnum.INPUT_LOGIN_INFO_HAS_NULL_ERROR);
        }

        String username = authentication.getUsername();
        String password = authentication.getPassword();
        Integer roleType = authentication.getRoleType();
        if((StringEnum.EMPTY.getValue()).equals(username)
                || (StringEnum.EMPTY.getValue()).equals(password)
                || roleType == null){
            //抛出登录信息不完整异常
            throw new UserException(ResultEnum.INPUT_LOGIN_INFO_HAS_NULL_ERROR);
        }

        SecureUser secureUser = userLoginMapper.selectUserByAuthentication(authentication);

        if(secureUser == null){
            //抛出账户密码错误或用户不存在的异常
            throw new UserException(ResultEnum.USERNAME_PASSWORD_ERROR);
        }

        return secureUser;
    }
}
