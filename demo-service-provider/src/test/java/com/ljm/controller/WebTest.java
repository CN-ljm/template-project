package com.ljm.controller;

import com.ljm.base.utils.AppContextUtil;
import com.ljm.controller.web1.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebTest {

    @Autowired
    private Hello hello;

    @Test
    public void controllerAopTest() {
//        hello.sayHello();
        Hello bean = AppContextUtil.getAppContext().getBean(Hello.class);
        bean.sayHello();
    }

}
