package com.coder_mart_server.user.user_model.entity;

import com.coder_mart_server.public_modules.model.entity.BaseEntity;
import com.coder_mart_server.user.user_modules.user_teacher.pojo.dto.ClassInfoChangeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 班级花名册表实体
 */
@Getter
@Setter
@ToString
public class ClassRosterEntity extends BaseEntity implements Serializable {
    //对应班级id
    private Long classId;

    //学生id
    private Long studentId;
}


















