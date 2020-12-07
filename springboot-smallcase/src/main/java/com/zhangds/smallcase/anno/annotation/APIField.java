package com.zhangds.smallcase.anno.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface APIField {
    int order() default -1;
    int length() default -1;
    String type() default "";
}
