package com.zhangds.mybatisplus.service.impl;

import com.zhangds.mybatisplus.entities.Permission;
import com.zhangds.mybatisplus.mapper.PermissionMapper;
import com.zhangds.mybatisplus.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
