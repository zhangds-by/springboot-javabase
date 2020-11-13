package com.zhangds.mybatisplus.service.impl;

import com.zhangds.mybatisplus.entities.Role;
import com.zhangds.mybatisplus.mapper.RoleMapper;
import com.zhangds.mybatisplus.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
