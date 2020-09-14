package com.zhangds.shiro.mapper;

import com.zhangds.shiro.entities.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
