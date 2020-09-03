package com.zhangds.spring.aop.config;

import com.zhangds.spring.aop.service.CglibComp;
import com.zhangds.spring.aop.service.UserService;
import com.zhangds.spring.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@ComponentScan(value = "com.zhangds.spring.aop.service.*")
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public UserService target() {
        return new UserServiceImpl();
    }

    @Bean
    public CglibComp CglibTarget() {
        return new CglibComp();
    }

}
