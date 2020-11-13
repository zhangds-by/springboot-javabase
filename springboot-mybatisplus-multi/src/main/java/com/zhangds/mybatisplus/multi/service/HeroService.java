package com.zhangds.mybatisplus.multi.service;

import com.zhangds.mybatisplus.multi.common.PageResult;
import com.zhangds.mybatisplus.multi.entities.Hero;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangds
 * @since 2020-09-10
 */
public interface HeroService extends IService<Hero> {

    List<Hero> findSearch(Map searchMap);

    PageResult<Hero> findSearch(Map searchMap, int page, int size);
}
