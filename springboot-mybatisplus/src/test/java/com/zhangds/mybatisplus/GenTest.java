package com.zhangds.mybatisplus;

import com.baidu.fsg.uid.UidGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MybatisPlusApplication.class)
public class GenTest {

    @Autowired
    private UidGenerator cachedUidGenerator;

    @Test
    public void createUid(){
        System.out.println(cachedUidGenerator.getUID());
    }
}
