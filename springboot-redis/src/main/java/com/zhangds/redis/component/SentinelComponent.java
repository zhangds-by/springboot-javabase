package com.zhangds.redis.component;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 主从复制
 * @author: zhangds
 * @date: 2021/7/23 14:27
 */
@Component
public class SentinelComponent {

    private JedisSentinelPool jedisSentinelPool=null;

    public Jedis getJedisFromSentinel(){
        if (jedisSentinelPool == null){
            Set<String> sentinelSet = new HashSet<>();
            sentinelSet.add("192.168.25.128:26379");
            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(100); //最大可用连接数
            jedisPoolConfig.setMaxIdle(50); //最大闲置连接数
            jedisPoolConfig.setMinIdle(50); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试pingpong
            jedisSentinelPool = new JedisSentinelPool("mymaster", sentinelSet, jedisPoolConfig);
            return jedisSentinelPool.getResource();
        }else{
            return jedisSentinelPool.getResource();
        }
    }
}
