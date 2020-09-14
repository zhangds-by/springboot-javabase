package com.zhangds.shiro.mapper;

import com.zhangds.shiro.entities.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhangds.shiro.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPermissionByRole(Role role);
}
