package com.zhangds.mybatisplus.aspect;

import com.zhangds.mybatisplus.enums.DBTypeEnum;
import com.zhangds.mybatisplus.model.DataSourceContextHolder;
import com.zhangds.mybatisplus.model.DynamicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -100)
@Aspect
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    @Pointcut("execution(* com.zhangds.mybatisplus.mapper..*.*(..))")
    private void memberAspect() {
    }

    @Pointcut("execution(* com.zhangds.mybatisplus.mapper..*.*(..))")
    private void orderAspect() {
    }

    @Before("memberAspect()")
    public void member() {
        LOGGER.info("切换到member 数据源...");
        DataSourceContextHolder.setDbType(DBTypeEnum.member);
    }

    @Before("orderAspect()")
    public void order() {
        LOGGER.info("切换到order 数据源...");
        DataSourceContextHolder.setDbType(DBTypeEnum.order);
    }
}
