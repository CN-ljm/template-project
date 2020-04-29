package com.ljm.utils;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title 加签、验签工具类
 * @Desc 用于HTTP请求产生签名和验证签名
 */
public class Sha1withRSAUtil {

    /** 指定加密算法为RSA */
    private static String ALGORITHM = "RSA";
    /** 指定key的大小 */
    private static int KEYSIZE = 1024;

    /**
     * 生成公私密钥对
     * @param keyPath 公私钥存放路径
     */
    public static void generatorKeyPair(String keyPath){
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            //找一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            //创建密钥对生成器
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
            //初始化生成器
            kpg.initialize(KEYSIZE, sr);
            //生成公钥私钥
            KeyPair keyPair = kpg.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            //写成入文件（写入对象、写入字符串）
            File file = new File(keyPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.out.println(file.getPath());
            oos = new ObjectOutputStream(new FileOutputStream(file.getPath()+"/private"));
            oos.writeObject(privateKey);
            oos = new ObjectOutputStream(new FileOutputStream(file.getPath()+"/public"));
            oos.writeObject(publicKey);
            fos = new FileOutputStream(file.getPath() +"/private.txt");
            fos.write((new BASE64Encoder()).encode(privateKey.getEncoded()).getBytes());
            fos.flush();
            fos = new FileOutputStream(file.getPath() +"/public.txt");
            fos.write((new BASE64Encoder()).encode(publicKey.getEncoded()).getBytes());

            oos.close();
            oos.close();
            fos.flush();
            fos.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            closeCloseable(oos);
            closeCloseable(fos);
            e.printStackTrace();
        }
    }

    //关闭流
    private static void closeCloseable(Closeable close){
        if (close == null) {
            return;
        }
        try {
            close.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
