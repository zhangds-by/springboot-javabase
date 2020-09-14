package com.zhangds.shiro.service.impl;

import com.zhangds.shiro.entities.Log;
import com.zhangds.shiro.mapper.LogMapper;
import com.zhangds.shiro.service.LogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
