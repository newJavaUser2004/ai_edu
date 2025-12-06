package com.coder_mart_server.public_modules.util;

import com.coder_mart_server.public_modules.model.enums.StringEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * redis封装工具
 */
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public static void init(RedisTemplate redisTemplateParam){
        redisTemplate = redisTemplateParam;
    }

    /**
     * 生成key
     * @param keys
     * @return
     */
    public static String buildKey(Object...keys) {
        if (keys == null) {
            return StringEnum.EMPTY.getValue();
        }
        StringJoiner keyString = new StringJoiner(":");
        for(Object key : keys){
            keyString.add(String.valueOf(key));
        }
        return keyString.toString();
    }

    /**
     * redis的String类型操作
     */
    public class RedisString{

        /**
         * 将对象存入redis的String结构中
         * @param value
         */
        public static <T> void addObjectToString(String key,T value){
            redisTemplate.opsForValue().set(key,value);
        }

        /**
         * 设置过期时间
         * @param key
         * @param value
         * @param time
         * @param timeUnit
         * @param <T>
         */
        public static <T> void addObjectToString(String key, T value, Long time, TimeUnit timeUnit){
            redisTemplate.opsForValue().set(key,value,time,timeUnit);
        }

        /**
         * 添加元素，当该key为空时
         * @param key
         * @param value
         * @param <T>
         */
        public static <T> void addObjectToStringIfNoHave(String key,T value){
            redisTemplate.opsForValue().setIfAbsent(key,value);
        }

        /**
         * 添加元素，当该key为空时，同时设置过期时间
         * @param key
         * @param value
         * @param time
         * @param timeUnit
         * @param <T>
         */
        public static <T> void addObjectToStringIfNoHave(String key,T value,Long time,TimeUnit timeUnit){
            redisTemplate.opsForValue().setIfAbsent(key,value,time,timeUnit);
        }

        /**
         * 从String中获取内容
         * @param key
         * @return
         */
        public static Object getObjectInString(String key){
            return redisTemplate.opsForValue().get(key);
        }

        /**
         * 从String中获取指定类型
         * @param key
         * @param type
         * @param <T>
         * @return
         */
        public static <T> T getObjectInString(String key,Class<T> type){
            Object objectInString = getObjectInString(key);
            return type.cast(objectInString);
        }

    }

    /**
     * redis的Hash类型操作
     */
    public class RedisHash{

        /**
         * 将单个键值对放入hash
         * @param key
         * @param hashKey
         * @param hashValue
         * @param <T>
         */
        public static <T> void addParamToHash(String key,String hashKey,T hashValue){
            redisTemplate.opsForHash().put(key,hashKey,hashValue);
        }

        /**
         * 将hashmap存入hash
         * @param key
         * @param hashMap
         * @param <T>
         */
        public static <T> void addMapToHash(String key, Map hashMap){
            redisTemplate.opsForHash().putAll(key, hashMap);
        }

        /**
         * 从hash中获取value
         * @param key
         * @param hashKey
         * @return
         */
        public static Object getParamInHash(String key,String hashKey){
            return redisTemplate.opsForHash().get(key, hashKey);
        }

        /**
         * 从hash中获取value
         * @param key
         * @param hashKey
         * @param type
         * @param <T>
         * @return
         */
        public static <T> T getParamInHash(String key,String hashKey,Class<T> type){
            Object paramInHash = getParamInHash(key, hashKey);
            return type.cast(paramInHash);
        }

        /**
         * 获取所有hash key
         * @param key
         * @return
         */
        public static Set<String> getHashKeyInHash(String key){
            return redisTemplate.opsForHash().keys(key);
        }

        /**
         * 获取所有的值
         * @param key
         * @return
         */
        public static List getHashValueInHash(String key){
            return redisTemplate.opsForHash().values(key);
        }

        /**
         * 获取hashMap
         * @param key
         * @return
         */
        public static Map getMapInHash(String key){
            return redisTemplate.opsForHash().entries(key);
        }

        /**
         * 删除指定的hash key
         * @param key
         * @param hashKey
         */
        public static void deleteHashKeyInHash(String key,String hashKey){
            redisTemplate.opsForHash().delete(key,hashKey);
        }

        /**
         * 批量删除hash key
         * @param key
         * @param hashKeys
         */
        public static void deleteHashKeyInHash(String key,List<String> hashKeys){
            String[] strings = (String[]) hashKeys.toArray();
            redisTemplate.opsForHash().delete(key,strings);
        }
    }

    /**
     * redis的List操作类型
     */
    public class RedisList{
    }

    /**
     * redis的Set操作类型
     */
    public class RedisSet{
    }

    /**
     * redis的ZSet操作类型
     */
    public class RedisZSet{
    }
}








































