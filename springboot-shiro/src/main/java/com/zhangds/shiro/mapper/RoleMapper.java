package com.zhangds.shiro.mapper;

import com.zhangds.shiro.entities.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhangds.shiro.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRoleByUser(User user);

}
