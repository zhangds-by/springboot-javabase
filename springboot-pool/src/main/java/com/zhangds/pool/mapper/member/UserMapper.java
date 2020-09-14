package com.zhangds.pool.mapper.member;

import com.zhangds.pool.entities.member.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
