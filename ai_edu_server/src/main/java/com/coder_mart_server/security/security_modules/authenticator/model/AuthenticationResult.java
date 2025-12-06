package com.coder_mart_server.security.security_modules.authenticator.model;

import com.coder_mart_server.security.security_model.entity.SecureUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationResult extends AbstractAuthentication {

    private String token;

    private SecureUser userInfo;
}
