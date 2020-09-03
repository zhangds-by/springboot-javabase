package com.zhangds.spring.aop.service.impl;

import com.zhangds.spring.aop.service.IAddition;
import org.springframework.stereotype.Component;

@Component
public class AdditionImpl implements IAddition {
    @Override
    public void addtional() {
        System.out.println("out additional...");
    }
}
