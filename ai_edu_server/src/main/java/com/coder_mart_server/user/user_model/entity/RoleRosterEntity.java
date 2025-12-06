package com.coder_mart_server.user.user_model.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 职位花名册表实体
 */
@Getter
@Setter
@ToString
public class RoleRosterEntity extends BaseEntity implements Serializable {
    //用户id（包括学生和老师）
    private Long userId;

    //职位id
    private Long roleId;
}












