package com.coder_mart_server.public_modules.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("my-config.snow")
@Getter
@Setter
@ToString
public class SnowProperties {
    //开始时间，2025/8/11
    long startTime;

    //机房id
    long computerRoomId;

    //机器id
    long computerId;
}
