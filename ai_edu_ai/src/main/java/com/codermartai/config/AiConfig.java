package com.codermartai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient tongYiAiChatClient(OpenAiChatModel openAiChatModel){
        return ChatClient
                .builder(openAiChatModel)
                .defaultSystem("你是一个强大的ai助手，旨在帮助用户解答疑惑") //可选
                .build();
    }
}
