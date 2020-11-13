package com.zhangds.mybatisplus.multi.enums;

public enum DBTypeEnum {

    member("member"),
    order("order");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
