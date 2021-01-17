package com.ljm.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
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
        param.put("name", "dragon");
//        param.put("age", 18);
        String privateKey = getPrivateKey();
        //加签
        String sign = Sha1withRSAUtil.sign(JSONObject.toJSONBytes(param, SerializerFeature.WRITE_MAP_NULL_FEATURES), privateKey);
        param.put("sign", sign);
        param.put("age", 11);

        String res = HttpClientUtil.doPost(postUrl, param);
        System.out.println(res);
    }

    private static String getPrivateKey() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/tmp/key/private.txt"));
            byte[] buffer = new byte[1024];
            int r = -1;
            StringBuilder sb = new StringBuilder();
            while ((r =bis.read(buffer)) != -1) {
//            System.out.println(new String(buffer, 0, r));
///            bis.read(buffer);
                sb.append(new String(buffer, 0, r));
            }
            String privateKey = sb.toString();
            bis.close();
            return privateKey;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

