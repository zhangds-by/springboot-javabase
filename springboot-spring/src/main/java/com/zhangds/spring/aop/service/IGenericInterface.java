package com.zhangds.spring.aop.service;

public interface IGenericInterface<T> {

    T get(T param);
}
