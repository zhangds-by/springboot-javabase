package com.zhangds.redis.lock.lua;

import com.zhangds.redis.lock.impl.SimpleRedisLockManager;

import javax.annotation.PostConstruct;

public class LuaSimpleRedisLockManager extends SimpleRedisLockManager {

    @PostConstruct
    public void init(){
        distributeLock = new LuaDistributeLock(redisTemplate, "mylock_", 5);
    }

}
