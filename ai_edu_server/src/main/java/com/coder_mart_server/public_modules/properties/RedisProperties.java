package com.coder_mart_server.public_modules.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("my-config.redis")
@Getter
@Setter
@ToString
public class RedisProperties {
    //ip地址
    String host;

    //端口
    String port;

    //数据库
    String database;
}
