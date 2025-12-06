package com.coder_mart_server.public_modules.exceptions.base;

import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 异常基类
 */
@Getter
@Setter
@ToString
public class BaseException extends RuntimeException{

    //异常结果枚举
    private ResultEnum resultEnum;

    public BaseException(String message) {
        super(message);
    }

}
