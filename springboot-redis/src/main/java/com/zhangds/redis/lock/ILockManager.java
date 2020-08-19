package com.zhangds.redis.lock;

/**
 * 封锁分布式锁
 * @author: zhangds
 * @date: 2020/8/19 10:20
 */
public interface ILockManager {

    /**
     * 通过加锁安全执行程序，无返回的数据
     * @param lockKeyName key名称
     * @param callback
     */
    void lockCallBack(String lockKeyName, SimpleCallBack callback);
    /**
     * 通过加锁安全执行程序，有返回数据
     * @param lockKeyName
     * @param callback
     * @return
     */
    <T> T lockCallBackWithRtn(String lockKeyName, ReturnCallBack<T> callback);
}
