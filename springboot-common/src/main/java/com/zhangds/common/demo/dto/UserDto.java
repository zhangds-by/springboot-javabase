package com.zhangds.common.demo.dto;

import com.zhangds.common.demo.entities.User;
import com.zhangds.common.interfaces.BaseForm;
import org.springframework.beans.BeanUtils;

public class UserDto implements BaseForm<User> {

    private String nickname;

    private String birthday;

    private String username;

    private String password;

    private String otherInfo;

    @Override
    public User buildEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
