package com.ljm.base.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author create by jiamingl on 下午4:31
 * @title
 * @desc
 */
@Slf4j
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        Subject subject = getSubject(req, resp);
        String[] rolesArray = (String[]) mappedValue;
        // 没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String s : rolesArray) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(s)) {
                return true;
            }
        }
        return false;
    }
}
