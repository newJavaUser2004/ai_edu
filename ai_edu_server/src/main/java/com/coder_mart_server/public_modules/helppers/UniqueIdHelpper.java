package com.coder_mart_server.public_modules.helppers;

import com.coder_mart_server.public_modules.properties.SnowProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 唯一id生成工具
 */
@Slf4j
public class UniqueIdHelpper {
    //雪花算法参数
    private final SnowProperties snowProperties;

    //开始时间戳
    private long startTime;

    //上次生成id的时间戳
    private long lastTime = 0l;

    //机房id
    private long computerRoomId;

    //机器id
    private long computerId;

    //序列号
    private long serialNum = 0l;

    //机房id比特位数
    private long computerRoomBytes = 5l;

    //机器id比特位数
    private long computerBytes = 5l;

    //序列号位数
    private long serialNumBytes = 12l;

    //最大机房id
    private long maxComputerRoomId = ~(-1l << computerRoomBytes);

    //最大机器id
    private long maxComputerId = ~(-1l << computerBytes);

    //最大序列号数
    private long maxSerialNum = ~(-1l << serialNumBytes);

    public UniqueIdHelpper(SnowProperties snowProperties) {
        this.snowProperties = snowProperties;
        this.startTime = snowProperties.getStartTime();
        this.computerRoomId = snowProperties.getComputerRoomId();
        this.computerId = snowProperties.getComputerId();
    }

    //生成雪花id
    public synchronized Long snowIdBuild(){
        //获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();

        //当发生时钟回拨，则抛出异常
        if(currentTimeMillis < lastTime){
            System.out.println("发生时钟回拨");
        }

        //同一时间生成，序列号+1
        if(currentTimeMillis == lastTime){
            //过界自动清零
            serialNum = (serialNum+1) & maxSerialNum;

            //已经过界线，重新获取时间戳
            if(serialNum == 0l){
                currentTimeMillis = waitNextMillis(currentTimeMillis);
            }
        }else{
            serialNum = 0l;
        }

        lastTime = currentTimeMillis;

        return(
                ((currentTimeMillis - startTime)<<(computerRoomBytes+computerBytes+serialNumBytes))
                |(computerRoomId<<(computerBytes+serialNumBytes))
                |(computerId<<serialNumBytes)
                |(serialNum)
                );
    }

    //获取下一个时间戳
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
