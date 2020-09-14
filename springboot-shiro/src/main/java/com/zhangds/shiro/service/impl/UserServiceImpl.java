package com.zhangds.shiro.service.impl;

import com.zhangds.shiro.entities.User;
import com.zhangds.shiro.mapper.UserMapper;
import com.zhangds.shiro.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.selectOne(new User(username));
    }
}
