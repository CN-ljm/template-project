package com.ljm.service.dubbo.impl;

import com.ljm.api.CommonApi;

public class CommonApiImpl implements CommonApi {

    @Override
    public long customerNo(String name) {
        System.out.println("Name is " + name);
        return 1007L;
    }
}
