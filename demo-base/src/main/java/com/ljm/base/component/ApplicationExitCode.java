package com.ljm.base.component;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Created by liangjiaming on 2020/7/13
 * @title 定制spring Application 推出返回码
 * @Desc
 */
@Component
public class ApplicationExitCode {

    @Bean
    public ExitCodeGenerator exitCodeGenerator(){
        return () -> 0;
    }

}
