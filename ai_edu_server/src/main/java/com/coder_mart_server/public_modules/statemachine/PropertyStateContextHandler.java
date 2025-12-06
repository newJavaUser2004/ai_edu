package com.coder_mart_server.public_modules.statemachine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 属性类型转换机
 */
public class PropertyStateContextHandler {

    //属性状态转换机
    private final static Map<Class, Function<String,Object>> PROPERTY_STATE_CONTEXT = new HashMap<>();

    /**
     * 初始化状态机
     */
    static {
        //Long类型
        PROPERTY_STATE_CONTEXT.put(Long.class,(str)->{
            return Long.parseLong(str);
        });

        //Integer类型
        PROPERTY_STATE_CONTEXT.put(Integer.class,(str)->{
            return Integer.parseInt(str);
        });

        //Double类型
        PROPERTY_STATE_CONTEXT.put(Double.class,(str)->{
            return Double.parseDouble(str);
        });

        //String类型
        PROPERTY_STATE_CONTEXT.put(String.class,(str)->{
            return str;
        });
    }

    /**
     * 根据类型，返回对应的类型转换方法
     */
    public static Object parseObjectByClassType(Class classType,Object value){
        Function<String, Object> stringObjectFunction = PROPERTY_STATE_CONTEXT.get(classType);
        return stringObjectFunction.apply((String) value);
    }
}
























