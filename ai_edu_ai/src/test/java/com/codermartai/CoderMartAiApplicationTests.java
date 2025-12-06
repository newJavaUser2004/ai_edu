package com.codermartai;

import com.codermartai.model.AiEntity;
import com.codermartai.agents.AiAgentCenter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CoderMartAiApplicationTests {

    @Autowired
    AiAgentCenter testOne;

    @Test
    void contextLoads() {
        String person = "你是一个任务分解者，你负责将传递给你的任务进行拆分，将一个大任务拆分为可实现的小任务。" +
                "要求：1.你拆分出的小任务要由其它ai执行，请确保你的小任务易被其它ai所理解，并执行;" +
                "2.请你专注完成拆分的任务，不要去管具体的实现措施;3.任务的拆分要有：该任务ai的提示词，告诉ai他是一个什么者、" +
                "要告诉ai他要做什么、要告诉ai他的任务完成结果要放在哪里。";
        String work = "我是一个21周岁的男生，身高169，体重149斤，我现在想进一步减肥，给我制定一个减肥方案。";
        String result = "请将结果映射到AiEntity类中，其中person为对该任务ai的描述，work为该ai所需完成的任务，" +
                "result为其任务返回的结果";
        List<AiEntity> aiEntities = testOne.callUserMessage(person + work + result,AiEntity.class);
        System.out.println("大任务拆分情况:"+aiEntities);
//        for(AiEntity aiEntity : aiEntities){
//            String s = aiEntity.person + "," + aiEntity.work + "," + aiEntity.result;
//            String s1 = testOne.testTheAiAgain(s);
//            System.out.println("小任务执行情况："+s1);
//        }
    }

}
