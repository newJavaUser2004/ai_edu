package com.codermartai.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ai智能体中心
 */
@Component
public class AiAgentCenter {

    private ChatClient chatClient;

    public AiAgentCenter(@Qualifier("tongYiAiChatClient") ChatClient chatClient){
        this.chatClient = chatClient;
    }

    /**
     * 和ai模型聊天
     * @param msg
     * @param resultType
     * @param <T>
     * @return
     */
    public <T> List<T> callUserMessage(String msg,Class<T> resultType){
        
        List<T> entity = chatClient.prompt()
                .user(msg)
                .call()
                .entity(new ParameterizedTypeReference<List<T>>() {
                });

        return entity;
    }

    /**
     * 和ai模型聊天
     * @param msg
     * @return
     */
    public String callUserMessage(String msg){

        String content = chatClient.prompt()
                .user(msg)
                .call()
                .content();

        return content;
    }
}
