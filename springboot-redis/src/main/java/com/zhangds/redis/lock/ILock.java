package com.zhangds.redis.lock;

/**
 * 
 * @author: zhangds
 * @date: 2020/8/19 9:50
 */
public interface ILock {
    void lock(String lock);
    
    void unlock(String lock);
}
