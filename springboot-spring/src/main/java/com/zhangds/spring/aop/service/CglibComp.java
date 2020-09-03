package com.zhangds.spring.aop.service;

import org.springframework.stereotype.Component;

/**
 * cglib代理真实对象
 * @author: zhangds
 * @date: 2020/8/27 12:08
 */
@Component
public class CglibComp {

    public void someLogic() {
        System.out.println("component");
    }

}
