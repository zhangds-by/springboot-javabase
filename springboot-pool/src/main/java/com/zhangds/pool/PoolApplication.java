package com.zhangds.pool;

import com.zhangds.pool.common.Result;
import com.zhangds.pool.config.member.MemberConfig;
import com.zhangds.pool.config.order.OrderConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan(basePackages = "com.zhangds.pool.**")
@EnableConfigurationProperties({MemberConfig.class, OrderConfig.class})
@RestController
@RequestMapping("app")
public class PoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoolApplication.class, args);
    }

    @RequestMapping("/test")
    public Result result(){
        return Result.success("application test api");
    }
}
