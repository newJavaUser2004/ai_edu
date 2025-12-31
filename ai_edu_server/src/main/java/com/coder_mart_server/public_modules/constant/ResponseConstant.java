package com.coder_mart_server.public_modules.constant;

/**
 * 响应码常量
 */
public class ResponseConstant {

    //响应码
    public class Code{
        //成功响应码
        public static final int SUCCESS_CODE = 200;

        //代码异常响应码
        public static final int CODE_ERROR_CODE = 500;
    }

    //异常信息
    public class ErrorMessage{
        //token过期
        public static final String TOKEN_OVER_TIME = "token过期或未登录";

        //输入的认证信息为null
        public static final String INPUT_LOGIN_INFO_HAS_NULL = "输入的认证信息不完整";

        //账户密码不存在
        public static final String USERNAME_PASSWORD_ERROR = "账户密码错误或用户不存在";

        //权限不足
        public static final String ROLE_ERROR = "权限不足";

        //参数为null
        public static final String PARAM_IS_NULL = "参数异常";

        //班级不存在
        public static final String CLASS_NULL = "班级不存在";

        //班级人数已满
        public static final String CLASS_STUDENT_NUM_MAX = "班级人数已满";

        //非本人操作
        public static final String ACTION_USER_NOT_OWN = "非本人操作";

        //空文件上传
        public static final String UPLOAD_FILE_IS_EMPTY = "上传文件为空";

        //文件无拓展名
        public static final String UPLOAD_FILE_HAS_NO_EXTENSION = "上传文件无拓展名";
    }

    //响应体类型
    public class ContentType{
        //编码格式
        private static final String ENCODING_FORMAT = "charset=UTF-8";

        //json
        public static final String JSON_TYPE = "application/json; "+ENCODING_FORMAT;

        //文本
        public static final String TEXT_TYPE = "text/plain; "+ENCODING_FORMAT;
    }
}
