package com.coder_mart_server.public_modules.handlers;

import com.coder_mart_server.public_modules.exceptions.base.BaseException;
import com.coder_mart_server.public_modules.model.results.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    //所有自定义异常抓取
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public Result catchAllError(BaseException baseException){
        return Result.error(baseException.getResultEnum());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result throwCustomException(MethodArgumentNotValidException exception){

        return Result.error(exception.getBindingResult().getFieldError().getDefaultMessage());
    }
}
