package com.zhangds.mybatisplus.entities;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String operation;
    @TableField("operation_module")
    private String operationModule;
    private String username;
    @TableField("date_time")
    private Date dateTime;
    private String url;
    private String respParam;
    private String errorLogFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationModule() {
        return operationModule;
    }

    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRespParam() {
        return respParam;
    }

    public void setRespParam(String respParam) {
        this.respParam = respParam;
    }

    public String getErrorLogFlag() {
        return errorLogFlag;
    }

    public void setErrorLogFlag(String errorLogFlag) {
        this.errorLogFlag = errorLogFlag;
    }

    @Override
    public String toString() {
        return "Log{" +
        ", id=" + id +
        ", operation=" + operation +
        ", operationModule=" + operationModule +
        ", username=" + username +
        ", dateTime=" + dateTime +
        ", url=" + url +
        ", respParam=" + respParam +
        ", errorLogFlag=" + errorLogFlag +
        "}";
    }
}
