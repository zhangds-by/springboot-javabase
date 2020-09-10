package com.zhangds.mybatisplus.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhangds.mybatisplus.common.PageResult;
import com.zhangds.mybatisplus.entities.Hero;
import com.zhangds.mybatisplus.mapper.HeroMapper;
import com.zhangds.mybatisplus.service.HeroService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private HeroMapper heroMapper;

    @Override
    public List<Hero> findSearch(Map searchMap) {
        EntityWrapper<Hero> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        return heroMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Hero> findSearch(Map searchMap, int page, int size) {
        EntityWrapper<Hero> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        Page<Hero> userPage = new Page<>(page,size);
        List<Hero> list = heroMapper.selectPage(userPage, wrapper);
        return new PageResult<>(userPage.getTotal(),list);
    }

    /**
     * 构造查询条件
     * @param searchMap
     * @return
     */
    public EntityWrapper<Hero> createSearchCondition(Map searchMap) {
        EntityWrapper<Hero> wrapper = new EntityWrapper<>(new Hero());
        if (searchMap.get("name") != null) {
            wrapper.eq("name", searchMap.get("name"));
        }
        return wrapper;
    }
}
