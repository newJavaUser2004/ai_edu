package com.coder_mart_server.public_modules.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class RedissonUtil {

    private static RedissonClient redissonClient;

    public static void init(RedissonClient redisson){
        redissonClient = redisson;
    }

    /**
     * 等待锁
     * @param lockName
     * @param supplier
     * @param <R>
     * @return
     * @throws Exception
     */
    public static <R> R tryLock(String lockName, Supplier<R> supplier) throws Exception{
        RLock lock = redissonClient.getLock(lockName);
        boolean isLock = false;
        try {
            //todo 设置默认时间
            isLock = lock.tryLock(1, 1, TimeUnit.SECONDS);
            if(isLock){
                R result = supplier.get();
                return result;
            }
            //todo 设置自定义异常
            throw new Exception();
        }finally {
            if(isLock && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}















