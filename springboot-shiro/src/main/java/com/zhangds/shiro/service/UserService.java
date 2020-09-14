package com.zhangds.shiro.service;

import com.zhangds.shiro.entities.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
public interface UserService extends IService<User> {

    User getUserByName(String username);
}
