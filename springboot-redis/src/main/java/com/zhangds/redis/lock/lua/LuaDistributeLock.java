package com.zhangds.redis.lock.lua;

import com.zhangds.redis.lock.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LuaDistributeLock implements ILock {

    private static final Logger logger = LoggerFactory.getLogger(LuaDistributeLock.class);

    private static final int LOCK_MAX_EXIST_TIME = 5;  // 单位s，一个线程持有锁的最大时间
    private static final String LOCK_PREX = "lock_"; // 作为锁的key的前缀

    private StringRedisTemplate redisTemplate;
    private String lockPrex; // 做为锁key的前缀
    private int lockMaxExistTime; // 单位s，一个线程持有锁的最大时间

    private DefaultRedisScript<Long> lockScript; // 锁脚本
    private DefaultRedisScript<Long> unlockScript; // 解锁脚本

    private ThreadLocal<String> threadKeyId = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };  // 线程变量

    public LuaDistributeLock(StringRedisTemplate redisTemplate) {
        this(redisTemplate, LOCK_PREX, LOCK_MAX_EXIST_TIME);
    }

    public LuaDistributeLock(StringRedisTemplate redisTemplate, String lockPrex, int lockMaxExistTime) {
        this.redisTemplate = redisTemplate;
        this.lockPrex = lockPrex;
        this.lockMaxExistTime = lockMaxExistTime;
        init();
    }

    /**
     * 生成相应的 DefaultRedisScript 对象
     * @author: zhangds
     * @date: 2020/8/19 11:09
     */
    public void init() {
        // Lock script
        lockScript = new DefaultRedisScript<Long>();
        lockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("com/zhangds/redis/lock/lua/lock.lua")));
        lockScript.setResultType(Long.class);
        // unlock script
        unlockScript = new DefaultRedisScript<Long>();
        unlockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("com/zhangds/redis/lock/lua/unlock.lua")));
        unlockScript.setResultType(Long.class);
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
        Assert.notNull(lock, "lock can't be null!");
        String lockKey = getLockKey(lock);
        while(true){
            List<String> keyList = new ArrayList<String>();
            keyList.add(lockKey);
            keyList.add(threadKeyId.get());
            if(redisTemplate.execute(lockScript, keyList, String.valueOf(lockMaxExistTime * 1000)) > 0){
                break;
            }else{
                try {
                    // 短暂休眠，nano避免出现活锁
                    Thread.sleep(10, (int)(Math.random() * 500));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    @Override
    public void unlock(String lock) {
        final String lockKey = getLockKey(lock);
        List<String> keyList = new ArrayList<String>();
        keyList.add(lockKey);
        keyList.add(threadKeyId.get());
        redisTemplate.execute(unlockScript, keyList);
    }

    private String getLockKey(String lock){
        StringBuilder sb = new StringBuilder();
        sb.append(lockPrex).append(lock);
        return sb.toString();
    }
}
