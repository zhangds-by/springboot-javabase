package com.zhangds.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KeyTest {

    /**
     * 操作字符串
     */
    @Test
    public void oprateString(){

        Jedis jedis = new Jedis("192.168.25.128", 6379);
        jedis.set("java", "100");
        jedis.set("python", "200");
        jedis.set("c++", "300");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());

        System.out.println(jedis.exists("java"));
        System.out.println(jedis.ttl("java"));
        System.out.println(jedis.get("java"));

        jedis.mset("p1", "zhangds", "p2", "zhangsan", "p3", "lisi");
        System.out.println(jedis.mget("p1", "p2", "p3"));
    }

    /**
     * 操作set
     */
    @Test
    public void oprateSet(){

        Jedis jedis = new Jedis("192.168.25.128", 6379);
        // 值不能重复
        jedis.sadd("user:id", "10086", "10010", "10000", "10010");
        System.out.println(jedis.smembers("user:id"));
        System.out.println(jedis.srem("user:id", "10086")); // 删除成功返回1
        System.out.println(jedis.smembers("user:id"));
    }

    /**
     * 操作list
     */
    @Test
    public void oprateList(){

        Jedis jedis = new Jedis("192.168.25.128", 6379);
//        jedis.lset("learnlist", 0, "java");  // 使用lset，key必须存在
//        jedis.lset("learnlist", 1, "C++");
//        jedis.lset("learnlist", 2, "c#");
        jedis.lpush("learnlist", "java", "C++", "c#");
        System.out.println(jedis.lrange("learnlist", 0, -1));

    }

    /**
     * 操作hash
     */
    @Test
    public void oprateHash(){

        Jedis jedis = new Jedis("192.168.25.128", 6379);
        jedis.hset("user", "name", "zhangds");
        System.out.println(jedis.hget("user", "name"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "123");
        map.put("name", "zhangds");
        map.put("age", "18");
        jedis.hmset("user", map);
        System.out.println(jedis.hmget("user", "id", "name"));
    }

    /**
     * 操作zset：关联一个score
     */
    @Test
    public void oprateZset(){

        Jedis jedis = new Jedis("192.168.25.128", 6379);
        jedis.zadd("math", 60, "zhangds");
        jedis.zadd("math", 100, "lisi");
        jedis.zadd("math", 20, "wangwu");
        System.out.println(jedis.zrange("math", 0, -1));
    }
}
