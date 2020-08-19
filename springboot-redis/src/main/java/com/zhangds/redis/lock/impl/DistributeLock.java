package com.zhangds.redis.lock.impl;

import com.zhangds.redis.lock.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DistributeLock implements ILock {

    private static final Logger logger = LoggerFactory.getLogger(DistributeLock.class);

    private static final int LOCK_MAX_EXIST_TIME = 5;  // 单位s，一个线程持有锁的最大时间
    private static final String LOCK_PREX = "lock_"; // 作为锁的key的前缀

    private StringRedisTemplate redisTemplate;
    private String lockPrex; // 做为锁key的前缀
    private int lockMaxExistTime; // 单位s，一个线程持有锁的最大时间

    private ThreadLocal<String> threadId = new ThreadLocal<String>();  // 线程变量

    public DistributeLock(StringRedisTemplate redisTemplate) {
        this(redisTemplate, LOCK_PREX, LOCK_MAX_EXIST_TIME);
    }

    public DistributeLock(StringRedisTemplate redisTemplate, String lockPrex, int lockMaxExistTime) {
        this.redisTemplate = redisTemplate;
        this.lockPrex = lockPrex;
        this.lockMaxExistTime = lockMaxExistTime;
    }

    /**
     * 确保分布式锁的可用：
     *      互斥性：通过 threadId 保存每个线程锁的 UUID 值，用于区分当前锁是否为自己所有，并且锁的 value 也存储此值
     *      避免死锁：获取锁后，需要为锁设置一个有效期，锁也可以被自动释放
     *      锁重入：
     * @author: zhangds
     * @date: 2020/8/19 10:11
     */
    @Override
    public void lock(String lock) {
        Assert.notNull(lock, "lock cannot be null");
        String lockKey = getLockKey(lock);
        BoundValueOperations<String,String> keyBoundValueOperations = redisTemplate.boundValueOps(lockKey);
        while(true) {
            // 实现锁的可重入
            String value = keyBoundValueOperations.get();
            // 根据传入的值，判断用户是否持有这个锁
            if (value != null && value.equals(String.valueOf(threadId.get()))) {
                // 重置过期时间
                keyBoundValueOperations.expire(lockMaxExistTime, TimeUnit.SECONDS);
                break;
            }

            if (keyBoundValueOperations.setIfAbsent(lockKey)) {
                // 每次获取锁时，必须重新生成id值
                String keyUniqueId = UUID.randomUUID().toString(); // 生成key的唯一值
                threadId.set(keyUniqueId);
                // 显设置value，再设置过期日期，否则过期日期无效
                keyBoundValueOperations.set(String.valueOf(keyUniqueId));
                // 为了避免一个用户拿到锁后，进行过程中没有正常释放锁，这里设置一个默认过期实际，避免死锁
                keyBoundValueOperations.expire(lockMaxExistTime, TimeUnit.SECONDS);
                break;
            } else {
                try {
                    // 短暂休眠，nano避免出现活锁
                    Thread.sleep(10, (int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    @Override
    public void unlock(String lock) {
        final String lockKey = getLockKey(lock);
        BoundValueOperations<String,String> keyBoundValueOperations = redisTemplate.boundValueOps(lockKey);
        String lockValue = keyBoundValueOperations.get();
        if(!StringUtils.isEmpty(lockValue) && lockValue.equals(threadId.get())){
            redisTemplate.delete(lockKey);
        }else{
            logger.warn("key=[{}]已经释放，本次不执行释放. 线程Id[{}] ", lock, lockValue);
        }
    }

    private String getLockKey(String lock){
        StringBuilder sb = new StringBuilder();
        sb.append(lockPrex).append(lock);
        return sb.toString();
    }
}
