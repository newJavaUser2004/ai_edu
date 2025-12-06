package com.coder_mart_server.security.security_model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
public class SecureUser implements Serializable {

    //用户id
    Long userId;

    //权限(有多个身份)
    List<String> roles;

    //上次更新时间
    Long lastUpdateTime;
}
