package com.zhangds.redis.service;

import com.google.common.collect.Lists;
import com.zhangds.redis.dto.UserVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    List<UserVo> userList = Lists.newArrayList();

    /**
     * 查询数据，缓存中存在直接获取缓存
     * @author: zhangds
     * @date: 2020/8/19 16:32
     */
    @Cacheable(cacheNames = "userInfo", key = "zhangds")
    public UserVo getUserInfo(){
        UserVo userVo = new UserVo();
        userVo.setUsername("zhangds");
        userVo.setAge(12);
        userVo.setStatus("1");
        return userVo;
    }

    /**
     * 更新缓存
     *      参数和Cacheable一致
     *      方法返回值一致
     * @author: zhangds
     * @date: 2020/8/19 16:34
     */
    @CachePut(cacheNames = "userInfo", key = "zhangds")
    public UserVo updateUser(){
        UserVo userVo = new UserVo();
        userVo.setUsername("zhangsan");
        userVo.setAge(18);
        userVo.setStatus("1");
        return userVo;
    }

    @CacheEvict(cacheNames = "userInfo", key = "zhangds")
    public void deleteUser(Integer userNo){
        UserVo user = userList.get(userNo);
    }

}
