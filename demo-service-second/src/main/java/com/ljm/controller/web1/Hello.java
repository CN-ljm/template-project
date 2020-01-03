package com.ljm.controller.web1;

import com.ljm.service.CustomerCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视图控制器
 */
@Api("hello视图控制器")
@RestController
@RequestMapping("/hello")
public class Hello {

    @Autowired
    private CustomerCommonService service;

    @ApiOperation("测试swagger")
    @GetMapping("/sayHello")
    public String sayHello(){
        service.getCustomerNoByNames("world!");
        return "hello!";
    }

}
