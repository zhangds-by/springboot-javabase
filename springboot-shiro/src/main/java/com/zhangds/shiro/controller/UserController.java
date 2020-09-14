package com.zhangds.shiro.controller;


import com.zhangds.pool.common.Result;
import com.zhangds.pool.exception.MyException;
import com.zhangds.shiro.aop.SysLogAop;
import com.zhangds.shiro.entities.User;
import com.zhangds.shiro.service.UserService;
import com.zhangds.shiro.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangds
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @SysLogAop(operation = "登录",operateModule = "用户模块")
    public Result login(@RequestBody User u) {
        User user = userService.getUserByName(u.getUsername());
        if (user == null) {
            throw new MyException(401,"该用户不存在");
        }
        if (user.getPassword().equals(u.getPassword())) {
            return Result.success(JWTUtil.sign(u.getUsername(), u.getPassword()));
        } else {
            throw new MyException(401,"Unauthorized登录失败");
        }
    }

    @GetMapping("/checkAuth")
    @SysLogAop(operation = "检查用户权限",operateModule = "用户模块")
    public Result checkAuth() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Result.success("You are already logged in");
        } else {
            return Result.success("You are guest");
        }
    }

    @GetMapping("/requireAuth")
    @RequiresAuthentication
    public Result requireAuth() {
        return Result.success("You are authenticated");
    }

    @GetMapping("/requireRole")
    @RequiresRoles("admin")
    public Result requireRole() {
        try {
            return Result.success("You are visiting require_role");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/requirePermission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public Result requirePermission() {
        return Result.success("You are visiting permission require edit,view");
    }

}

