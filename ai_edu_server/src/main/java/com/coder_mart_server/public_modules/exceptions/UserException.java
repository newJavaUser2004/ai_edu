package com.coder_mart_server.public_modules.exceptions;

import com.coder_mart_server.public_modules.exceptions.base.BaseException;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;

/**
 * 用户异常
 */
public class UserException extends BaseException {

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.setResultEnum(resultEnum);
    }
}
