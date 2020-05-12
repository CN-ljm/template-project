package com.ljm.utils;

import java.io.IOException;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title
 * @Desc
 */
public class SecureApp {
    public static void main(String[] args) throws IOException {
        Sha1withRSAUtil.generatorKeyPair("D:/key");

        /*byte[] data = "name=aaa".getBytes();
        //加签
        String sign = Sha1withRSAUtil.sign(data, Sha1withRSAUtil.getPrivateKey("D:/key/private.txt"));
        System.out.println("签名是：" + sign);
        //验签
        data = "name=aaa".getBytes();
        boolean verify = Sha1withRSAUtil.verify(data, sign, Sha1withRSAUtil.getPublicKey("D:/key/public.txt"));
        System.out.println("验签结果是：" + verify);*/
    }
}
