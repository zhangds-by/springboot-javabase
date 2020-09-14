package com.zhangds.shiro.aop;

public class SysLogDTO {
    /**
     * 操作模块
     * @return
     */
    String operateModule;

    /**
     * 操作内容
     * @return
     */
    String operation;

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
