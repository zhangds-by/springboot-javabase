package com.zhangds.pool.mybatisplus.service.impl;

import com.zhangds.pool.mybatisplus.entities.Hero;
import com.zhangds.pool.mybatisplus.mapper.HeroMapper;
import com.zhangds.pool.mybatisplus.service.HeroService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-10
 */
@Service
public class HeroServiceImpl extends ServiceImpl<HeroMapper, Hero> implements HeroService {

}
