package com.coder_mart_server.public_modules.model.results;

import com.coder_mart_server.public_modules.constant.ResponseConstant;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应结果
 */
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {

    //响应码
    int code;

    //响应数据
    T date;

    //响应异常数据
    String message;

    /**
     * 有数据成功（响应码为200）
     * @param date
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T date){
        Result<T> result = new Result<>();
        result.setCode(ResponseConstant.Code.SUCCESS_CODE);
        result.setDate(date);
        result.setMessage(null);
        return result;
    }

    /**
     * 无数据成功（响应码为200）
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setCode(ResponseConstant.Code.SUCCESS_CODE);
        result.setDate(null);
        result.setMessage(null);
        return result;
    }

    /**
     * 根据枚举返回异常
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getResultCode());
        result.setMessage(resultEnum.getMessage());
        result.setDate(null);
        return result;
    }

    /**
     * 根据异常信息返回异常
     * @param message
     * @return
     */
    public static Result error(String message){
        Result result = new Result();
        result.setCode(ResponseConstant.Code.CODE_ERROR_CODE);
        result.setMessage(message);
        result.setDate(null);
        return result;
    }
}












