package com.ljm.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.base.mapper.*;
import com.ljm.model.sys.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author create by jiamingl on 下午10:28
 * @title
 * @desc
 */
@Service
@Slf4j
public class SysUserAuthorService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserPermissionMapper sysUserPermissionMapper;

    @Autowired
    private SysUserRoleRefMapper sysUserRoleRefMapper;

    @Autowired
    private SysUserRolePermissionRefMapper sysUserRolePermissionRefMapper;

    @Autowired
    private SysUrlPermissionRefMapper sysUrlPermissionRefMapper;


    /**
     * 查询用户角色
     * @param username
     * @return
     */
    public List<SysUserRole> listRoleByUsername(String username) {
        Wrapper<SysUser> q1 = new QueryWrapper<>(SysUser.builder().username(username).build());
        SysUser user = sysUserMapper.selectOne(q1);
        if (user == null) {
            return Collections.EMPTY_LIST;
        }

        Wrapper<SysUserRoleRef> q2 = new QueryWrapper<>(SysUserRoleRef.builder().sysUserId(user.getId()).build());
        List<SysUserRoleRef> sysUserRoleRefs = sysUserRoleRefMapper.selectList(q2);
        if (sysUserRoleRefs.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<Long> roleIds = sysUserRoleRefs.stream().map(SysUserRoleRef::getRoleId).collect(Collectors.toList());
        return sysUserRoleMapper.selectBatchIds(roleIds);
    }

    /**
     * 通过权限ID找角色
     * @param permId
     * @return
     */
    public List<SysUserRole> listARolesByPermId(Long permId) {
        QueryWrapper q1 = new QueryWrapper(SysUserRolePermissionRef.builder().permissionId(permId).build());
        List<SysUserRolePermissionRef> refList = sysUserRolePermissionRefMapper.selectList(q1);
        List<Long> roleIds = refList.stream().map(SysUserRolePermissionRef::getRoleId).collect(Collectors.toList());
        return sysUserRoleMapper.selectBatchIds(roleIds);
    }

    /**
     * getPermissionById
     * @param permId
     * @return
     */
    public SysUserPermission getPermissionById(Long permId) {
        return sysUserPermissionMapper.selectById(permId);
    }

    /**
     * 通过角色ID查权限
     * @param roleIds
     * @return
     */
    public List<SysUserPermission> listPermissionByRoleIds(List<Long> roleIds) {
        QueryWrapper<SysUserRolePermissionRef> q = new QueryWrapper();
        q.in("role_id", roleIds);
        List<SysUserRolePermissionRef> refList = sysUserRolePermissionRefMapper.selectList(q);
        List<Long> permIds = refList.stream().map(SysUserRolePermissionRef::getPermissionId).collect(Collectors.toList());
        return sysUserPermissionMapper.selectBatchIds(permIds);
    }

    /**
     * 查询所有url权限相关
     * @return
     */
    public List<SysUrlPermissionRef> listAllUrlPermission() {
        return sysUrlPermissionRefMapper.selectList(null);
    }
}
