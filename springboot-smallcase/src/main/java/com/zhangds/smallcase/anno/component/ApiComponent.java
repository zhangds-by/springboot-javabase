package com.zhangds.smallcase.anno.component;

import com.zhangds.smallcase.anno.annotation.API;
import com.zhangds.smallcase.anno.annotation.APIField;
import com.zhangds.smallcase.anno.entities.AbstractAPI;

import java.util.Arrays;
import java.util.Comparator;

public class ApiComponent {

    private static String remoteCall(AbstractAPI api){
        API apiAnno = api.getClass().getAnnotation(API.class);
        apiAnno.url();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(apiAnno.getClass().getDeclaredFields()) //获取所有字段
                .filter(field -> field.isAnnotationPresent(APIField.class)) //查找标记了注解的字段
                .sorted(Comparator.comparingInt(t -> t.getAnnotation(APIField.class).order())) //根据注解中的order进行排序
                .peek(field -> field.setAccessible(true)) //可以访问私有字段
                .forEach(field -> {
                    APIField apiField = field.getAnnotation(APIField.class);
                    Object value = "";
                    try {
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //根据某个字段的值对数据进行处理
                    switch (apiField.type()){
                        case "S": {
                            break;
                        }
                        default:
                            break;
                    }
                });
        return sb.toString();
    }
}
