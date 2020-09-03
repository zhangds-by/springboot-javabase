package com.zhangds.spring.aop.aspect;

import com.zhangds.spring.aop.service.IAddition;
import com.zhangds.spring.aop.service.impl.AdditionImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advice {

    //使用AOP动态添加父类、接口、字段
    //为UserServiceImpl额外添加IAddition接口，并使用AdditionImpl作为其实现
    @DeclareParents(value = "com.zhangds.spring.aop.service.UserService+",
            defaultImpl = AdditionImpl.class)
    public IAddition addition;

    @Before("execution(* com.zhangds.spring.aop.*Service.*(..))")
    public void advisor() {
        System.out.println("do before");
    }

}
