package com.coder_mart_server.public_modules.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求解析器
 */
public class HttpParseUtil {

    /**
     * 解析请求体为json
     * @param request
     * @return
     */
    public static String parseHttpBodyToJSON(HttpServletRequest request) throws IOException {
        StringBuffer jsonString = new StringBuffer();
        String jsonLine;

        BufferedReader jsonReader = request.getReader();

        while((jsonLine = jsonReader.readLine()) != null){
            jsonString.append(jsonLine);
        }

        return jsonString.toString();
    }

    /**
     * 解析请求体为对象
     * @param request
     * @param classType
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T parseHttpBodyToObject(HttpServletRequest request, Class<T> classType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //解析json字符串
        String json = parseHttpBodyToJSON(request);

        //将json转化为java对象
        return objectMapper.readValue(json, classType);
    }

    /**
     * 解析请求头
     * @param request
     * @param headName
     * @return
     */
    public static String parseHttpHead(HttpServletRequest request,String headName){
        return request.getHeader(headName);
    }

    /**
     * 批量解析请求头
     * @param request
     * @param headNames
     * @return
     */
    public static Map parseHttpHead(HttpServletRequest request,String...headNames){
        HashMap<String, String> httpHeadMap = new HashMap<>();
        for(String headName : headNames){
            String headValue = parseHttpHead(request, headName);
            httpHeadMap.put(headName,headValue);
        }
        return httpHeadMap;
    }
}





















