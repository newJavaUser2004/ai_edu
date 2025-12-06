package com.coder_mart_server.public_modules.model.entity;

import com.coder_mart_server.user.user_constant.EntityDefaultConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
/**
 * 基础实体类(所有实体类的父类)
 */
public class BaseEntity implements Serializable {
    //创建时间
    protected LocalDateTime createTime;

    //更新时间
    protected LocalDateTime updateTime;

    //版本号
    protected Integer version;

    //逻辑删除
    protected Integer deleted;

    public BaseEntity(){
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.version = EntityDefaultConstant.BaseEntityConstant.DEFAULT_VERSION;
        this.deleted = EntityDefaultConstant.BaseEntityConstant.DEFAULT_DELETED;
    }
}





















