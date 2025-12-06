package com.coder_mart_server.user.user_model.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户表实体
 */
@Getter
@Setter
@ToString
public class UserEntity extends BaseEntity implements Serializable {
    //用户id
    private Long userId;

    //用户名
    private String username;

    //密码
    private String password;

    //昵称
    private String name;

    //年龄
    private Integer age;
}













