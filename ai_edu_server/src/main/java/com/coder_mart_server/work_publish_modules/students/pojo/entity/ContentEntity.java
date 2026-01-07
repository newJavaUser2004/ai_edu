package com.coder_mart_server.work_publish_modules.students.pojo.entity;


import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContentEntity extends BaseEntity implements Serializable {

    private Long contentId;

    private String content;

}
