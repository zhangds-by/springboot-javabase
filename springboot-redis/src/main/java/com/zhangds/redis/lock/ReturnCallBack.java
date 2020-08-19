package com.zhangds.redis.lock;

/**
 * 有返回数据的回调函数
 * @author: zhangds
 * @date: 2020/8/19 10:22
 */
public interface ReturnCallBack<T> {
    T execute();
}
