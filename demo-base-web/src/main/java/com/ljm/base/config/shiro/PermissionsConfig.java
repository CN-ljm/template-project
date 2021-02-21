package com.ljm.base.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author create by jiamingl on 下午5:00
 * @title
 * @desc
 */
@Service
@Slf4j
public class PermissionsConfig {

    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
         配置不会被拦截的链接 顺序判断
        authc 必须认证通过才可以访问;
        anon 可以匿名访问
        */
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        /* 登录 */
        filterChainDefinitionMap.put("/login", "anon");
        // 退出登录
        filterChainDefinitionMap.put("/logout", "anon");
        // 未登录
        filterChainDefinitionMap.put("/unLogin", "anon");
        // 未授权
        filterChainDefinitionMap.put("/unauth", "anon");

        // 自定义权限
        /*List<Menu> permissionList = menuMapper.selectAll();
        if (!CollectionUtils.isEmpty(permissionList)) {
            permissionList.forEach(e -> {
                if (StringUtils.isNotBlank(e.getUrl())) {
                    // 根据url查询相关联的角色名,拼接自定义的角色权限
                    List<Role> roleList = roleMapper.selectRoleByMenuId(e.getMenuId());
                    StringJoiner MyRoles = new StringJoiner(",", "customRoles[", "]");
                    if (!CollectionUtils.isEmpty(roleList)) {
                        roleList.forEach(f -> {
                            MyRoles.add(f.getRoleDes());
                        });
                    }
                    filterChainDefinitionMap.put(e.getUrl(), "customAuthc," + MyRoles.toString() + ",customPerms[" + e.getResource() + "]");
                }
            });
        }*/
        filterChainDefinitionMap.put("/**", "customAuthc");
        return filterChainDefinitionMap;
    }

    /**
     * 更新权限,解决需要重启tomcat才能生效权限的问题
     */
    public void updatePermission() {
        log.info("-----------------正在更新权限...--------------");
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            ServletContext servletContext = request.getSession().getServletContext();
            //注意bean的名字不要写错了 是自己的shiroFilter名称
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean("shiroFilter");
            synchronized (shiroFilter) {
                // 获取过滤管理器
                PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
                // 清空初始权限配置
                manager.getFilterChains().clear();
                // 重新获取资源
                Map<String, String> chains = loadFilterChainDefinitions();
                for (Map.Entry<String, String> entry : chains.entrySet()) {
                    String url = entry.getKey();
                    String chainDefinition = entry.getValue().trim().replace(" ", "");
                    manager.createChain(url, chainDefinition);
                }
                log.info("-----------------更新权限成功!!--------------");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
