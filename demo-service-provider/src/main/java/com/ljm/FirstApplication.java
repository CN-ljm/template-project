package com.ljm;

import com.esotericsoftware.minlog.Log;
import com.ljm.base.listener.MyApplicationStartedEventListener;
import com.ljm.base.listener.MyMyApplicationEventListener;
import com.ljm.base.utils.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 应用启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DubboAutoConfiguration.class})
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class FirstApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FirstApplication.class);
        // 添加事件监听
        app.addListeners(new MyApplicationStartedEventListener());
        app.addListeners(new MyMyApplicationEventListener());
        ConfigurableApplicationContext applicationContext = app.run(args);
        /*ExitCodeGenerator bean = AppContextUtil.getBean(ExitCodeGenerator.class);
        int exit = SpringApplication.exit(applicationContext, bean);
        System.out.println(exit);*/

        log.info("哈哈哈");
    }
}
