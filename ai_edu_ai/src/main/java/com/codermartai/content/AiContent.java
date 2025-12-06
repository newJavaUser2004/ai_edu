package com.codermartai.content;

public class AiContent {

    //任务分解者
    public static final String DECOMPOSER_PERSON =
            "你是一个任务分解者，你负责将传递给你的任务进行拆分，将一个大任务拆分为可实现的小任务。" +
            "要求：1.你拆分出的小任务要由其它ai执行，请确保你的小任务易被其它ai所理解，并执行;" +
            "2.请你专注完成拆分的任务，不要去管具体的实现措施;" +
            "3.任务的拆分要有：该任务ai的提示词，告诉ai他是一个什么者、" +
            "要告诉ai他要做什么、要告诉ai他的任务完成结果要放在哪里。";

    //最终目的
    public static final String objective =
            "请将结果映射到AiEntity类中，其中person为对该任务ai的描述，work为该ai所需完成的任务，" +
            "result为其任务返回的结果";
}
