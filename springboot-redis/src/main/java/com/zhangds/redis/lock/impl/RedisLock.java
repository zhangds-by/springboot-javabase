package com.zhangds.redis.lock.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLock implements Lock {


    private String key;
    private long timeout;
    private TimeUnit timeUnit;
    private RedisTemplate redisTemplate;
    private DefaultRedisScript defaultRedisScript;
    private ThreadLocal<String> localUUID = new ThreadLocal<>();

    public RedisLock(String key, long timeout, TimeUnit timeUnit, RedisTemplate redisTemplate, DefaultRedisScript defaultRedisScript) {
        this.key = key;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.redisTemplate = redisTemplate;
        this.defaultRedisScript = defaultRedisScript;
    }

    public RedisLock(RedisTemplate redisTemplate, String key) {
        this.key = key;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void lock() {
        if (tryLock()) {
            return;
        }
        try {
            Thread.sleep(10);//性能浪费
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock();
    }


    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString();
        if (redisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, timeUnit)) {
            localUUID.set(uuid);
            return true;
        }
        return false;
    }

    @Override
    public void unlock() {
        String value = localUUID.get();
        redisTemplate.execute((RedisConnection connection) -> connection.eval(
                defaultRedisScript.getScriptAsString().getBytes(),
                ReturnType.INTEGER,
                1,
                key.getBytes(),
                value.getBytes()));
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
}
