package com.coder_mart_server.work_publish_modules.teachers.pojo.vo;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter

public class ResourceVO {
    //资源名
    private String resourceName;
    //url列表
    private String url;
    //资源id
    private Long resourceId;


    public ResourceVO(String resourceName, String url, Long resourceId) {
        this.resourceName = resourceName;
        this.url = url;
        this.resourceId = resourceId;
    }
}
