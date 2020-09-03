package com.zhangds.spring.aop.service.impl;

import com.zhangds.spring.aop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * jdk代理真实对象，实现接口
 * @author: zhangds
 * @date: 2020/8/27 12:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}
