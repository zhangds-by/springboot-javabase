package com.zhangds.redis;

import com.zhangds.redis.component.SentinelComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SentinelComponent sentinelComponent;

    /**
     * redis模板使用
     */
    @Test
    public void testTemplate(){
        redisTemplate.opsForValue().set("template01", "redis01");
        System.out.println(redisTemplate.opsForValue().get("template01"));
    }

    /**
     * redis事务操作
     */
    @Test
    public void testTransaction(){
        Jedis jedis = new Jedis("192.168.25.128", 6379);
        Transaction multi = jedis.multi();
        multi.decr("balance");
        List<Object> exec = multi.exec();
        jedis.close();
    }

    @Test
    public void testSentinel(){
        Jedis jedis = sentinelComponent.getJedisFromSentinel();
        System.out.println(jedis.get("share"));
    }

    @Test
    public void jedisClusterTest(){
        Set<HostAndPort> set =new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.25.128", 6379));
        JedisCluster jedisCluster=new JedisCluster(set);
        jedisCluster.set("k1", "v1");
        System.out.println(jedisCluster.get("k1"));
    }

}
