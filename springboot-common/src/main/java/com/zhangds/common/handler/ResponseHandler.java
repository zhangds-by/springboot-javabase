package com.zhangds.common.handler;

import java.io.Serializable;

public class ResponseHandler implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String ERROR = "系统异常， 请联系管理员";
    public static final String CD0[] = {"200", "操作成功"};
    public static final String CD1[] = {"300", "操作失败"};
    public static final String CD2[] = {"301", "会话超时,请重新登录"};
    public static final String CD3[] = {"400", "请重新获取"};

    public ResponseHandler() {}

    public ResponseHandler(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ResponseHandler(String code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public static ResponseHandler success() {
        return new ResponseHandler(ResponseHandler.CD0[0], ResponseHandler.CD0[1]);
    }

    public static ResponseHandler success(Object data) {
        return new ResponseHandler(ResponseHandler.CD0[0], ResponseHandler.CD0[1], data);
    }

    public static ResponseHandler fail() {
        return new ResponseHandler(ResponseHandler.CD1[0], ResponseHandler.CD1[1]);
    }

    public static ResponseHandler fail(String desc) {
        return new ResponseHandler(ResponseHandler.CD1[0], desc);
    }

    public static ResponseHandler fail(Object data) {
        return new ResponseHandler(ResponseHandler.CD1[0], ResponseHandler.CD1[1], data);
    }

    public static ResponseHandler fail(String desc, Object data) {
        return new ResponseHandler(ResponseHandler.CD1[0], desc, data);
    }

    public static ResponseHandler timeout() {
        return new ResponseHandler(ResponseHandler.CD2[0], ResponseHandler.CD2[1]);
    }

    public static ResponseHandler timeout(String desc) {
        return new ResponseHandler(ResponseHandler.CD2[0], desc);
    }

    public static ResponseHandler retry() {
        return new ResponseHandler(ResponseHandler.CD3[0], ResponseHandler.CD3[1]);
    }

    public static ResponseHandler retry(String desc) {
        return new ResponseHandler(ResponseHandler.CD3[0], desc);
    }

    private String code;
    private String desc;
    private Object data;
    private String errorCode;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

