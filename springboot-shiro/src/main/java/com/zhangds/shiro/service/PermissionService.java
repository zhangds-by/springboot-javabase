package com.zhangds.shiro.service;

import com.zhangds.shiro.entities.Permission;
import com.baomidou.mybatisplus.service.IService;
import com.zhangds.shiro.entities.Role;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findPermissionByRole(Role role);
}
