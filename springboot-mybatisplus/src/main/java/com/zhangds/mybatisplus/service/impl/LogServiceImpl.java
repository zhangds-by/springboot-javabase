package com.zhangds.mybatisplus.service.impl;

import com.zhangds.mybatisplus.entities.Log;
import com.zhangds.mybatisplus.mapper.LogMapper;
import com.zhangds.mybatisplus.service.LogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-11-02
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
