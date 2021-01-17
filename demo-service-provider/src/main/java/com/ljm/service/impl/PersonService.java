package com.ljm.service.impl;

import com.ljm.controller.web1.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by liangjiaming on 2020/6/22
 * @title
 * @Desc
 */
@Service
public class PersonService {

    @Autowired
    private Hello hello;

    public String getUserName() {
        return "AAA";
    }

}
