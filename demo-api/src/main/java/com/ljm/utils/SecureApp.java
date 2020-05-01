package com.ljm.utils;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title
 * @Desc
 */
public class SecureApp {
    public static void main(String[] args) {
        Sha1withRSAUtil.generatorKeyPair("/tmp/key/");
    }
}
