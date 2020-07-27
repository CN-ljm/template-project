package com.ljm.base.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by liangjiaming on 2020/7/10
 * @title
 * @Desc
 */
@Component
@Slf4j
public class Mybean {

    public Mybean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        log.info("NonOptionArgs: {}", files.toString());
    }

}
