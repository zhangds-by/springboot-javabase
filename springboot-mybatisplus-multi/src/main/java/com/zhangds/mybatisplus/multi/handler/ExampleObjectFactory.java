package com.zhangds.mybatisplus.multi.handler;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * 自定义对象工厂
 * @author: zhangds
 * @date: 2020/10/15 14:05
 */
public class ExampleObjectFactory extends DefaultObjectFactory {
    public Object create(Class type) {
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    /**
     * <objectFactory type="org.mybatis.example.ExampleObjectFactory">
     *   <property name="someProperty" value="100"/>
     * </objectFactory>
     * property 的值属性会被传递给 setProperties 方法
     */
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
