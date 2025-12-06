package com.coder_mart_server.public_modules.config;

import com.coder_mart_server.public_modules.properties.RedisProperties;
import com.coder_mart_server.public_modules.util.RedisUtil;
import com.coder_mart_server.public_modules.util.RedissonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * redis配置
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    /**
     * redis基本模板类
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate redisTemplateBuild(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());

        //字符串序列化器存入redis后就是字符串，并不能直接与对象之间进行转换
        redisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //初始化工具
        RedisUtil.init(redisTemplate);

        return redisTemplate;
    }

    /**
     * redisson客户端配置
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+redisProperties.getHost()+":"+redisProperties.getPort())
                .setDatabase(Integer.valueOf(redisProperties.getDatabase()));

        RedissonClient redissonClient = Redisson.create(config);

        RedissonUtil.init(redissonClient);

        return redissonClient;
    }
}
