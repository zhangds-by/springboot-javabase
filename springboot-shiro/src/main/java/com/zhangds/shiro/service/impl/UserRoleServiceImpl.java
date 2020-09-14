package com.zhangds.shiro.service.impl;

import com.zhangds.shiro.entities.UserRole;
import com.zhangds.shiro.mapper.UserRoleMapper;
import com.zhangds.shiro.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
