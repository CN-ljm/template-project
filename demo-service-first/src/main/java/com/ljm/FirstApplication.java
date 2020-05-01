package com.ljm;

import org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 应用启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DubboAutoConfiguration.class})
public class FirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class,args);
    }
}
