package com.coder_mart_server.public_modules.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FieldDescriptorBO {

    //set方法名
    private String methodName;

    //参数类型
    private Class<?> propertyType;
}
