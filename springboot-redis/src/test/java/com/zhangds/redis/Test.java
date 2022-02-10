package com.zhangds.redis;

import com.zhangds.redis.lock.SimpleCallBack;
import com.zhangds.redis.lock.impl.SimpleRedisLockManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * 测试类报错 Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 * 需要指定主启动类
 * @author: zhangds
 * @date: 2020/8/19 14:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisApplication.class)
public class Test {

    @Autowired
    private SimpleRedisLockManager simpleRedisLockManager;

    @org.junit.Test
    public void redisLock(){
        simpleRedisLockManager.lockCallBack("distributeLock" + ThreadLocalRandom.current().nextInt(1000), new SimpleCallBack(){
            @Override
            public void execute() {
                System.out.println("lockCallBack");
            }
        });
    }

    @org.junit.Test
    public void testConnect(){
        Jedis jedis = new Jedis("192.168.25.128", 6379);
        System.out.println(jedis.ping());
        jedis.close();
    }

}
