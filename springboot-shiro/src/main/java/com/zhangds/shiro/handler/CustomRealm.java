package com.zhangds.shiro.handler;

import com.zhangds.shiro.entities.Permission;
import com.zhangds.shiro.entities.Role;
import com.zhangds.shiro.entities.User;
import com.zhangds.shiro.service.PermissionService;
import com.zhangds.shiro.service.RoleService;
import com.zhangds.shiro.service.UserService;
import com.zhangds.shiro.utils.JWTUtil;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户的权限授权和认证
 * @author: zhangds
 * @date: 2020/9/14 14:03
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    /**
     * 必须重写的方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 检测用户权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------授权 doGetAuthorizationInfo-------------");
        String username = JWTUtil.getUsername(principals.getPrimaryPrincipal().toString());
        User user = userService.getUserByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 查询用户角色
        List<Role> roles = roleService.findRoleByUser(new User(user.getUsername()));
        for (Role role : roles) {
            if (role != null) {
                // 添加角色
                simpleAuthorizationInfo.addRole(role.getName());
                // 根据用户角色查询权限
                List<Permission> permissions = permissionService.findPermissionByRole(role);
                for (Permission permission : permissions) {
                    if (permission != null) {
                        // 添加权限
                        simpleAuthorizationInfo.addStringPermission(permission.getPerCode());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        logger.info("--------------认证 doGetAuthenticationInfo-----------");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userService.getUserByName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
