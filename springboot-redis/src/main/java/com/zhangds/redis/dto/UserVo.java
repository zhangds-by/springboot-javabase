package com.zhangds.redis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserVo implements Serializable {

    private static final long serialVersionUID = 191555060471838777L;

    @JsonProperty("userNo")
    private Integer userNo;

    @JsonProperty("username")
    private String username;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("status")
    private String status;

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
