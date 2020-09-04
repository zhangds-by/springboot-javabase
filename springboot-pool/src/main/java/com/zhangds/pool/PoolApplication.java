package com.zhangds.pool;

import com.zhangds.pool.config.member.MemberConfig;
import com.zhangds.pool.config.order.OrderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MemberConfig.class, OrderConfig.class})
public class PoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoolApplication.class, args);
    }
}
