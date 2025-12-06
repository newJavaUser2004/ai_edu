package com.coder_mart_server.user.user_model.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 职位表实体
 */
@Getter
@Setter
@ToString
public class RoleEntity extends BaseEntity implements Serializable {
    //职位id
    private Long roleId;

    //职位类型（0为学生，1为老师）
    Integer role_type;

    //职位名称
    private String roleName;

    //权限内容
    private String roleContent;
}


















