package com.zhangds.mybatisplus.service;

import com.zhangds.mybatisplus.entities.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author zhangds
 * @since 2020-11-02
 */
public interface UserService extends IService<User> {

    User selectByUsername(String username);

}
