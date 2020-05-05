package com.ljm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title 加签、验签工具类
 * @Desc 用于HTTP请求产生签名和验证签名
 */
public class Sha1withRSAUtil {

    private static Logger log = Logger.getAnonymousLogger();

    /** 指定加密算法为RSA */
    private static String ALGORITHM = "RSA";
    /** 指定key的大小 */
    private static int KEYSIZE = 512;

    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    public static final String SIGNATURE_ALGORITHM2 = "SHA256withRSA";

    public static final String SIGNATURE_ALGORITHM_MD5 = "MD5withRSA";

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

    /**
     * 加签
     * @param data 数据
     * @param privateKey 私钥
     * @return
     */
    public static String sign(byte[] data, String privateKey){

        try {
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);

            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            // 取私钥对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM2);
            sign.initSign(priKey);
            sign.update(data);

            return new BASE64Encoder().encode(sign.sign());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验签
     * @param signData 验签内容
     * @param sign 签名
     * @param publicKey 公钥
     * @return
     */
    public static boolean verify(byte[] signData, String sign, String publicKey){

        try {
            byte[] publickey = new BASE64Decoder().decodeBuffer(publicKey);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publickey);

            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            PublicKey key = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM2);
            signature.initVerify(key);
            signature.update(signData);

            return signature.verify(new BASE64Decoder().decodeBuffer(sign));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * 获取公钥
     * @param path
     * @return
     */
    public static String getPublicKey(String path){
        String keyPath = path != null ? path : "/tmp/key/public.txt";
        BufferedInputStream bis = null;
        try {

            bis = new BufferedInputStream(new FileInputStream(keyPath));
            byte[] buffer = new byte[1024];
            int r = -1;
            StringBuilder sb = new StringBuilder();
            while ((r =bis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, r));
            }
            String privateKey = sb.toString();
            return privateKey;

        }catch (IOException e){
            log.log(Level.WARNING, "获取公钥错误", e);
        }finally {
            closeCloseable(bis);
        }
        return null;
    }

    /**
     * 获取私钥
     * @param path
     * @return
     */
    public static String getPrivateKey(String path){
        String keyPath = path != null ? path : "/tmp/key/private.txt";
        BufferedInputStream bis = null;
        try {

            bis = new BufferedInputStream(new FileInputStream(keyPath));
            byte[] buffer = new byte[1024];
            int r = -1;
            StringBuilder sb = new StringBuilder();
            while ((r =bis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, r));
            }
            String privateKey = sb.toString();
            return privateKey;

        }catch (IOException e){
            log.log(Level.WARNING, "获取私钥错误", e);
        }finally {
            closeCloseable(bis);
        }
        return null;
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
