package com.coder_mart_server.work_publish_modules.teachers.pojo.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源表
 * t_resource
 */
@Data
public class ResourceEntity extends BaseEntity implements Serializable {

    private Long id;

    //班级id
    private Long classId;

    //资源id
    private  Long resourceId;

    //文件url
    private String url;

    //资源名
    private String resourceName;

}
