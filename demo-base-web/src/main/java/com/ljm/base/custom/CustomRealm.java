package com.ljm.base.custom;

import com.alibaba.fastjson.JSON;
import com.ljm.base.service.SysUserAuthorService;
import com.ljm.model.sys.SysUser;
import com.ljm.model.sys.SysUserPermission;
import com.ljm.model.sys.SysUserRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUserAuthorService authorService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        log.info("授权:{}", user);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        List<SysUserRole> userRoles = authorService.listRoleByUsername(user.getUsername());
        Set<String> roles = userRoles.stream().map(role -> role.getRoleKey()).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roles);
        // 添加角色对应的权限
        List<Long> permIds = userRoles.stream().map(SysUserRole::getId).collect(Collectors.toList());
        List<SysUserPermission> sysUserPermissions = authorService.listPermissionByRoleIds(permIds);
        Set permissions = sysUserPermissions.stream().map(SysUserPermission::getPermKey).collect(Collectors.toSet());
//        Set permissions = new HashSet();
//        permissions.add("add");
//        permissions.add("update");
//        permissions.add("delete");
        simpleAuthorizationInfo.setObjectPermissions(permissions);

        // 返回权限信息
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户信息
        String name = token.getUsername();
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        // TODO 查询用户
        SysUser user = new SysUser();
        user.setUsername("admin");
        user.setPassword("123456");
        log.info("用户认证，认证信息：{}", JSON.toJSONString(user));

        if (user == null) {
            throw new UnknownAccountException("不存在该用户");
        }
        // 返回认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());

        return simpleAuthenticationInfo;
    }
}
