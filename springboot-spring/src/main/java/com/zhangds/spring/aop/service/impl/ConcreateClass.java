package com.zhangds.spring.aop.service.impl;

import com.zhangds.spring.aop.service.IGenericInterface;

public class ConcreateClass implements IGenericInterface<String> {
    @Override
    public String get(String param) {
        return "hello";
    }

    /*
    JVM为类自动生成桥接方法
    public Object get(Object param){
        return this.get((String)param);
    }

     */
}