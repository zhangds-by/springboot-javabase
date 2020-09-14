package com.zhangds.shiro.aop;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author: zhangds
 * @date: 2020/9/11 14:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAop {
    /**
     * 操作模块
     * @return
     */
    String operateModule() default "";

    /**
     * 操作内容
     * @return
     */
    String operation() default  "";
}
