package com.ljm.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by liangjiaming on 2020/4/23
 * @title
 * @Desc
 */
public class HttpApp {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String getUrl = "http://localhost:8082/hello/getInfo";
        String postUrl = "http://localhost:8082/hello/getMapInfo";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "小龙女");
        param.put("age", 18);
        String res = HttpClientUtil.doPost(postUrl, param);
        System.out.println(res);
    }
}

