package com.zhangds.smallcase.anno.entities;

import com.zhangds.smallcase.anno.annotation.API;
import com.zhangds.smallcase.anno.annotation.APIField;

@API(url = "/bank/createUser", desc = "创建用户接口")
public class CreateUserAPI extends AbstractAPI {
    @APIField(order = 1, type = "S", length = 10)
    private String name;
    @APIField(order = 2, type = "S", length = 18)
    private String identity;
    @APIField(order = 4, type = "S", length = 11) //注意这里的order需要按照API表格中的顺序
    private String mobile;
    @APIField(order = 3, type = "N", length = 5)
    private int age;
}
