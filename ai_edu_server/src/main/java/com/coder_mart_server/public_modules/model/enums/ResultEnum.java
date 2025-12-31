package com.coder_mart_server.public_modules.model.enums;

import com.coder_mart_server.public_modules.constant.ResponseConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 异常结果枚举
 */
@RequiredArgsConstructor
@Getter
public enum ResultEnum {

    //token过期异常
    TOKEN_OVER_TIME_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.TOKEN_OVER_TIME),

    //输入的认证信息不完整
    INPUT_LOGIN_INFO_HAS_NULL_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.INPUT_LOGIN_INFO_HAS_NULL),

    //账户密码错误或用户不存在
    USERNAME_PASSWORD_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.USERNAME_PASSWORD_ERROR),

    //权限异常
    ROLE_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.ROLE_ERROR),

    //参数异常
    PARAM_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.PARAM_IS_NULL),

    //班级不存在
    CLASS_NULL_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.CLASS_NULL),

    //班级人数已满
    CLASS_STUDENT_NUM_MAX_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.CLASS_STUDENT_NUM_MAX),

    //操作人异常
    ACTION_USER_ERROR(ResponseConstant.Code.CODE_ERROR_CODE,
            ResponseConstant.ErrorMessage.ACTION_USER_NOT_OWN
    );


    private final int resultCode;

    private final String message;
}
