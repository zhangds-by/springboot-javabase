package com.zhangds.redis.service;

import com.google.common.collect.Lists;
import com.zhangds.redis.dto.UserVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userInfo")
public class UserInfoService {

    List<UserVo> userList = Lists.newArrayList();

    /**
     * 查询数据，缓存中存在直接获取缓存
     * @author: zhangds
     * @date: 2020/8/19 16:32
     */
    @Cacheable(key = "zhangds")
    public UserVo getUserInfo(){
        UserVo userVo = new UserVo();
        userVo.setUsername("zhangds");
        userVo.setAge(12);
        userVo.setStatus("1");
        return userVo;
    }

    /**
     * 动态将key设置为方法参数/对象参数的某个属性
     * @author: zhangds
     * @date: 2020/8/19 16:52
     */
    @Cacheable(key = "#userNo")
    public UserVo getUserInfoById(@RequestParam("userNo") Integer userNo){
        UserVo userVo = userList.get(userNo);
        return userVo;
    }

    /**
     * 更新缓存
     *      参数和Cacheable一致
     *      方法返回值一致
     * @author: zhangds
     * @date: 2020/8/19 16:34
     */
    @CachePut(key = "#userVo.username")
    public UserVo updateUser(UserVo userVo){
        userVo.setUsername("zhangsan");
        userVo.setAge(18);
        userVo.setStatus("1");
        return userVo;
    }

    @CacheEvict(key = "zhangds")
    public void deleteUser(Integer userNo){
        UserVo user = userList.get(userNo);
    }

}
