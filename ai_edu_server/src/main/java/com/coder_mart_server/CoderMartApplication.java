package com.coder_mart_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy
public class CoderMartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoderMartApplication.class, args);
    }
}
