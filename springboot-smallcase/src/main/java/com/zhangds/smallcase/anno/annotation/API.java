package com.zhangds.smallcase.anno.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface API {
    String desc() default "";
    String url() default "";
}
