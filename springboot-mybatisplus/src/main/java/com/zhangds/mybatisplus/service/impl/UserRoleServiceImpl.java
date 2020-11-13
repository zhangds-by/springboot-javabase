package com.zhangds.mybatisplus.service.impl;

import com.zhangds.mybatisplus.entities.UserRole;
import com.zhangds.mybatisplus.mapper.UserRoleMapper;
import com.zhangds.mybatisplus.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
