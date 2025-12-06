package com.codermartai.model;

/**
 * 可以将其抽象看为一个ai实体，底层询问ai的工作会被封装，用户只需要操作AiEntity即可
 */
public class AiEntity {

    //对ai智能体的描述
    private String person;

    //ai智能体的工作内容
    private String work;

    //ai智能体的工作目的
    private String objective;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
