package com.zhangds.mybatisplus.mapper;

import com.zhangds.mybatisplus.entities.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

}
