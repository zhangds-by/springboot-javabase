package com.zhangds.pool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class FreemarkerController {

    @RequestMapping("/freemarkerIndex")
    public String freemarker(Map<String, Object> dataModel) {
        dataModel.put("name", "张三");
        dataModel.put("message", "hello world");

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "苹果");
        map1.put("price", 4.5);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "香蕉");
        map2.put("price", 6.3);
        list.add(map1);
        list.add(map2);
        dataModel.put("goodsList", list);
        dataModel.put("today", new Date());
        dataModel.put("number", 123456789L);
        return "index";
    }
}
