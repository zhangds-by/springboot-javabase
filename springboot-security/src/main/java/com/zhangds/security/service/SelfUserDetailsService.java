package com.zhangds.security.service;

import com.zhangds.mybatisplus.entities.User;
import com.zhangds.mybatisplus.service.UserService;
import com.zhangds.security.domain.SelfUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public SelfUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        User user =userService.selectByUsername(username);
        if (user!=null){
            // 组装参数
            SelfUserDetails selfUserDetails = new SelfUserDetails();
            BeanUtils.copyProperties(user, selfUserDetails);
            return selfUserDetails;
        }
        return null;
    }
}
