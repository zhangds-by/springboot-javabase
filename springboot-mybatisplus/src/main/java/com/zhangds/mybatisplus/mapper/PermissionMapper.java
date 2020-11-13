package com.zhangds.mybatisplus.mapper;

import com.zhangds.mybatisplus.entities.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

}
