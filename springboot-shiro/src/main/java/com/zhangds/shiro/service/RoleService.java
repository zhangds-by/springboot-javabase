package com.zhangds.shiro.service;

import com.zhangds.shiro.entities.Role;
import com.baomidou.mybatisplus.service.IService;
import com.zhangds.shiro.entities.User;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
public interface RoleService extends IService<Role> {

    List<Role> findRoleByUser(User user);
}
