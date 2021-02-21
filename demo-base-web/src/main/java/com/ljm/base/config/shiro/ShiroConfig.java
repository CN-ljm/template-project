package com.ljm.base.config.shiro;

import com.ljm.base.custom.CustomFormAuthenticationFilter;
import com.ljm.base.custom.CustomPermissionsAuthorizationFilter;
import com.ljm.base.custom.CustomRealm;
import com.ljm.base.custom.CustomRolesAuthorizationFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @title shiro配置类
 * @desc
 *
 * @author create by jiamingl on 2021/1/21 下午11:24
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private PermissionsConfig permissionsConfig;

    /*@Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }*/

    //将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //登出
//        map.put("/logout", "logout");
//        // swagger
//        map.put("/swagger**/**", "anon");
//        map.put("/webjars/**", "anon");
//        map.put("/v2/**", "anon");
//        //对所有用户认证
//        map.put("/**", "authc");

        // 添加自定义过滤器
        Map<String, Filter> customFilterMap = new HashMap<>();
        customFilterMap.put("customAuthc", new CustomFormAuthenticationFilter());
        customFilterMap.put("customPerms", new CustomPermissionsAuthorizationFilter());
        customFilterMap.put("customRoles", new CustomRolesAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(customFilterMap);

        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(permissionsConfig.loadFilterChainDefinitions());
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
