package com.coder_mart_server.security.security_properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties("my-config.token")
@ToString
@Getter
@Setter
public class TokenLiveProperties {

    //是否长期存获
    boolean longLive;

    //生存时间
    long liveTime;

    //生存时间单位
    TimeUnit timeUnit;
}
