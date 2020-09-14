package com.zhangds.shiro.service.impl;

import com.zhangds.shiro.entities.Role;
import com.zhangds.shiro.entities.User;
import com.zhangds.shiro.mapper.RoleMapper;
import com.zhangds.shiro.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUser(User user) {
        return roleMapper.findRoleByUser(user);
    }
}
