package com.zhangds.shiro.service.impl;

import com.zhangds.shiro.entities.Permission;
import com.zhangds.shiro.entities.Role;
import com.zhangds.shiro.mapper.PermissionMapper;
import com.zhangds.shiro.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRole(Role role) {
        return permissionMapper.findPermissionByRole(role);
    }
}
