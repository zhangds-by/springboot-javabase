package com.zhangds.redis.cache;

import redis.clients.jedis.Jedis;

/**
 * 缓存穿透互斥锁
 * @author: zhangds
 * @date: 2021/7/23 18:46
 */
public class Penetrate {
    private Jedis jedis;
    public static String getData(String key){
        return null;
    }
}
