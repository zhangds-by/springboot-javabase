package com.zhangds.mybatisplus.mapper;

import com.zhangds.mybatisplus.entities.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhangds
 * @since 2020-11-02
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

}
