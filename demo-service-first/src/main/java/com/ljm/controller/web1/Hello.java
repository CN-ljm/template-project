package com.ljm.controller.web1;

import com.ljm.base.event.MySpringApplicationEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 视图控制器
 */
@Api("hello视图控制器")
@RestController
@RequestMapping("/hello")
@Slf4j
public class Hello {

    @Resource
    private ApplicationContext applicationContext;

    @ApiOperation("测试swagger")
    @GetMapping("/sayHello")
    public String sayHello(){

        MySpringApplicationEvent event = new MySpringApplicationEvent(this, "testEvent");
        applicationContext.publishEvent(event);
        log.info("sayHello");
//        throw new RuntimeException("出事情啦！");

        return "hello!";
    }

}
