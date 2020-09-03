package com.zhangds.scheduling.enums;

import com.zhangds.common.interfaces.IDictEnum;

public enum SysJobStatus implements IDictEnum {
    STOP("0", "终止"),
    NORMAL("1", "正常"),
    FINISH("2", "结束")
    ;

    private String code;

    private String name;

    SysJobStatus() {
    }

    SysJobStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
