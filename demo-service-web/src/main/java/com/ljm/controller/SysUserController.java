package com.ljm.controller;

import com.ljm.service.Handle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by jiamingl on 下午11:38
 * @title
 * @desc
 */
@RestController
@Slf4j
@Api("用户管理")
public class SysUserController {


    @ApiOperation("登录")
    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);

        subject.login(usernamePasswordToken);

        return "login success!";
    }

    @ApiOperation("登录成功")
    @PostMapping("/index")
    public String index() {
        return "login success!";
    }

    @ApiOperation("登录成功")
    @PostMapping("/error")
    public String error() {
        return "login error!";
    }

}
