package com.ljm.service;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Created by liangjiaming on 2020/5/13
 * @title
 * @Desc
 */
@Component
public class Handle {

    public String handleRequest() throws IOException {
        return "aaa";
    }

}
