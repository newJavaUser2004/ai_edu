package com.coder_mart_server.public_modules.config;

import com.coder_mart_server.public_modules.properties.SnowProperties;
import com.coder_mart_server.public_modules.helppers.UniqueIdHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SnowUtilConfig {

    private final SnowProperties snowProperties;

    @Bean
    public UniqueIdHelpper createUniqueIdUtilBean(){
        UniqueIdHelpper uniqueIdHelpper = new UniqueIdHelpper(snowProperties);
        return uniqueIdHelpper;
    }
}
