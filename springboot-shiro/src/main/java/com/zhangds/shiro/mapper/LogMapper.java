package com.zhangds.shiro.mapper;

import com.zhangds.shiro.entities.Log;
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
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
