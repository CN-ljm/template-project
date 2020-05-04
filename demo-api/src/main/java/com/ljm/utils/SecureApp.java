package com.ljm.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title
 * @Desc
 */
public class SecureApp {
    public static void main(String[] args) throws IOException {
//        Sha1withRSAUtil.generatorKeyPair("/tmp/key/");

        byte[] data = "name=aaa".getBytes();
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
//        System.out.println(privateKey);

        bis = new BufferedInputStream(new FileInputStream("/tmp/key/public.txt"));
        buffer = new byte[1024];
        r = -1;
        sb = new StringBuilder();
        while ((r =bis.read(buffer)) != -1) {
//            System.out.println(new String(buffer, 0, r));
///            bis.read(buffer);
            sb.append(new String(buffer, 0, r));
        }
        String publicKey = sb.toString();
//        System.out.println(publicKey);

        //加签
        String sign = Sha1withRSAUtil.sign(data, privateKey);
        System.out.println("签名是：" + sign);
        //验签
        data = "name=aaa".getBytes();
        boolean verify = Sha1withRSAUtil.verify(data, sign, publicKey);
        System.out.println("验签结果是：" + verify);
    }
}
