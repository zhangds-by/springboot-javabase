package com.zhangds.mybatisplus.multi.controller;


import com.zhangds.mybatisplus.multi.common.Result;
import com.zhangds.mybatisplus.multi.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangds
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;
    /**
     * 条件查询
     */
    @PostMapping(value="/search")
    public Result findSearch(@RequestBody Map searchMap){
        return Result.success(heroService.findSearch(searchMap));
    }

    /**
     * 条件+分页
     * @param searchMap
     * @param page
     * @param size
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size){
        return Result.success(heroService.findSearch(searchMap,page,size));
    }
}

