package com.coder_mart_server.public_modules.handlers;

import com.coder_mart_server.public_modules.constant.ResponseConstant;
import com.coder_mart_server.public_modules.model.enums.ResultEnum;
import com.coder_mart_server.public_modules.model.results.Result;
import com.coder_mart_server.public_modules.util.HttpParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 响应处理器
 */
public class HttpResponseHandler {

    /**
     * 返回自定义异常响应
     * @param response
     * @param resultEnum
     * @throws IOException
     */
    public static void errorResponse(HttpServletResponse response, ResultEnum resultEnum) throws IOException {
        //设置响应体类型为json格式
        response.setContentType(ResponseConstant.ContentType.JSON_TYPE);

        //设置响应码
        response.setStatus(resultEnum.getResultCode());

        //设置响应体
        ServletOutputStream outputStream = response.getOutputStream();

        //创建异常返回对象，并解析为json
        Result errorResult = Result.error(resultEnum);
        ObjectMapper objectMapper = new ObjectMapper();
        String errorResultJson = objectMapper.writeValueAsString(errorResult);

        outputStream.write(errorResultJson.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        response.flushBuffer();
    }

    /**
     * 返回异常响应
     * @param response
     * @param message
     * @throws IOException
     */
    public static void errorResponse(HttpServletResponse response, String message) throws IOException {
        //设置响应体类型为json格式
        response.setContentType(ResponseConstant.ContentType.JSON_TYPE);

        //设置响应码
        response.setStatus(ResponseConstant.Code.CODE_ERROR_CODE);

        //设置响应体
        ServletOutputStream outputStream = response.getOutputStream();

        //创建异常返回对象，并解析为json
        Result errorResult = Result.error(message);
        ObjectMapper objectMapper = new ObjectMapper();
        String errorResultJson = objectMapper.writeValueAsString(errorResult);

        outputStream.write(errorResultJson.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        response.flushBuffer();
    }

    /**
     * 返回成功响应
     * @param response
     * @param result
     * @param <T>
     * @throws IOException
     */
    public static <T> void successResponse(HttpServletResponse response, Result<T> result) throws IOException{
        //设置响应体类型为json格式
        response.setContentType(ResponseConstant.ContentType.JSON_TYPE);

        //设置响应码
        response.setStatus(result.getCode());

        //设置响应体
        ServletOutputStream outputStream = response.getOutputStream();

        //返回对象解析为json
        ObjectMapper objectMapper = new ObjectMapper();
        String errorResultJson = objectMapper.writeValueAsString(result);

        outputStream.write(errorResultJson.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        response.flushBuffer();
    }
}
