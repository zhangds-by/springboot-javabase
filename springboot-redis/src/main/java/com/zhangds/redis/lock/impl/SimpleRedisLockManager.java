package com.zhangds.redis.lock.impl;

import com.zhangds.redis.lock.ILock;
import com.zhangds.redis.lock.ILockManager;
import com.zhangds.redis.lock.ReturnCallBack;
import com.zhangds.redis.lock.SimpleCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
public class SimpleRedisLockManager implements ILockManager {

    @Autowired
    protected StringRedisTemplate redisTemplate;

    protected ILock distributeLock;

    @PostConstruct
    public void init(){
        distributeLock = new DistributeLock(redisTemplate, "mylock_", 5);
    }

    @Override
    public void lockCallBack(String lockKeyName, SimpleCallBack callback) {
        Assert.notNull("lockKeyName","lockKeyName 不能为空");
        Assert.notNull("callback","callback 不能为空");
        try{
            distributeLock.lock(lockKeyName);
            callback.execute();
        }finally{
            distributeLock.unlock(lockKeyName);
        }
    }

    @Override
    public <T> T lockCallBackWithRtn(String lockKeyName, ReturnCallBack<T> callback) {
        Assert.notNull("lockKeyName","lockKeyName 不能为空");
        Assert.notNull("callback","callback 不能为空");
        try{
            distributeLock.lock(lockKeyName);
            return callback.execute();
        }finally{
            distributeLock.unlock(lockKeyName);
        }
    }
}
