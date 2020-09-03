package com.zhangds.spring.aop;

import com.zhangds.spring.aop.config.AopConfig;
import com.zhangds.spring.aop.service.CglibComp;
import com.zhangds.spring.aop.service.IAddition;
import com.zhangds.spring.aop.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 测试AopUtils中的方法，查看源码
 * @author: zhangds
 * @date: 2020/8/27 11:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AopConfig.class)    //指定加载的主配置类
public class AopTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CglibComp cglibComp;

    @Test
    public void testIsAop() {
        //一个对象是否为代理对象
        //SpringProxy   代理对象标识接口
        //Proxy.isProxyClass  是否是JDK的代理实现
        //ClassUtils.isCglibProxyClass 是否是cglib代理实现
        System.out.println(AopUtils.isAopProxy(userService));

        //是否是JDK代理对象
        System.out.println(AopUtils.isJdkDynamicProxy(userService));
    }

    @Test
    public void testTargetClass(){
        System.out.println(cglibComp.getClass());
        System.out.println(AopUtils.getTargetClass(cglibComp));
    }

    //调用真实对象的真实方法
    @Test
    public void testMostSpecificMethod() throws Exception {
        Method m = cglibComp.getClass().getMethod("someLogic");
        System.out.println(m);  //获取代理对象的方法，属于类上的方法，无法获取执行目标的方法
        Method msm = AopUtils.getMostSpecificMethod(m, AopUtils.getTargetClass(cglibComp));
        System.out.println(msm);
    }

    @Test
    public void testIntroductions(){
        ((IAddition)userService).addtional();
    }

    @Test
    public void TestMethod(){
//        ClassUtils.resolvePrimitiveClassName()
        System.out.println(ClassUtils.getShortName(getClass()));
        System.out.println("java.lang.string");
        System.out.println(ClassUtils.getShortNameAsProperty(getClass()));
    }
}
