package com.ljm;

import com.ljm.base.event.MySpringApplicationEvent;
import com.ljm.base.listener.MyApplicationStartedEventListener;
import com.ljm.base.listener.MyMyApplicationEventListener;
import org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 应用启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DubboAutoConfiguration.class})
public class FirstApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FirstApplication.class);
        // 添加事件监听
        app.addListeners(new MyApplicationStartedEventListener());
        app.addListeners(new MyMyApplicationEventListener());
        app.run(args);
    }
}
