package com.zhangds.common.interfaces;

/**
 * 封装对象的转换，减少service代码
 * @author: zhangds
 * @date: 2020/12/2 9:10
 */
public interface BaseForm<T> {

    T buildEntity();

}
