package com.zhangds.pool.service.impl.member;

import com.zhangds.pool.entities.member.User;
import com.zhangds.pool.mapper.member.UserMapper;
import com.zhangds.pool.service.member.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
