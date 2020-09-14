package com.zhangds.pool.controller.member;


import com.zhangds.pool.common.Result;
import com.zhangds.pool.entities.member.User;
import com.zhangds.pool.mapper.member.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/getUserById")
    public Result getUserById(@RequestParam String id){
        logger.info("请求参数过滤" + id);
        return Result.success(userMapper.selectById(id));
    }

    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody User user){
        return Result.success(userMapper.insert(user));
    }
}

