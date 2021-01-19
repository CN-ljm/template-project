package com.ljm.base.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title 加签、验签工具类
 * @Desc 用于HTTP请求产生签名和验证签名
 */
@Slf4j
public class Sha1withRSAUtil {

//    private static Logger log = Logger.getAnonymousLogger();

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
            fos.write(Base64.getEncoder().encode(privateKey.getEncoded()));
            fos.flush();
            fos = new FileOutputStream(file.getPath() +"/public.txt");
            fos.write(Base64.getEncoder().encode(publicKey.getEncoded()));

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
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
//            byte[] keyBytes = privateKey.getBytes();
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
            byte[] publickey = Base64.getDecoder().decode(publicKey);

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
            log.error( "获取公钥错误", e);
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
            log.error("获取私钥错误", e);
        }finally {
            closeCloseable(bis);
        }
        return null;
    }

    /**
     * 加载PEM文件形式私钥，私钥格式应该为 PKCS8
     * @param keyPath
     * @return
     */
    public static PrivateKey loadPEMPKCS8PrivateKey(String keyPath) {
        String strPrivateKey = "";
        BufferedReader privateKey = null;
        try {
            privateKey = new BufferedReader(new FileReader(keyPath));
            String line = "";
            while((line = privateKey.readLine()) != null){
                strPrivateKey += line;
            }
            privateKey.close();
            strPrivateKey = strPrivateKey.replace("-----BEGIN PRIVATE KEY-----","").replace("-----END PRIVATE KEY-----","");

            //获取KeyFactory，指定RSA算法
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            //将BASE64编码的私钥字符串进行解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] encodeByte = decoder.decodeBuffer(strPrivateKey);
            //将BASE64解码后的字节数组，构造成PKCS8EncodedKeySpec对象，生成私钥对象
            PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodeByte));

            return key;
        } catch (IOException ioe) {
            log.error("文件读取错误", ioe);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (InvalidKeySpecException e) {
            log.error("", e);
        } finally {
            if (privateKey != null) {
                try {
                    privateKey.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return null;
    }

    /**
     * 加载PEM文件形式公钥
     * @param keyPath
     * @return
     */
    public static PublicKey loadPEMPKCS8PublicKey(String keyPath) {
        String strPublicKey = "";
        BufferedReader publicKey = null;
        try {
            publicKey = new BufferedReader(new FileReader(keyPath));
            String line = "";
            while((line = publicKey.readLine()) != null){
                strPublicKey += line;
            }
            publicKey.close();
            strPublicKey = strPublicKey.replace("-----BEGIN PUBLIC KEY-----","").replace("-----END PUBLIC KEY-----","");

            //获取KeyFactory，指定RSA算法
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            //将BASE64编码的公钥字符串进行解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] encodeByte = decoder.decodeBuffer(strPublicKey);
            //将BASE64解码后的字节数组，构造成X509EncodedKeySpec对象，生成公钥对象
            PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(encodeByte));

            return key;
        } catch (IOException ioe) {
            log.error("文件读取错误", ioe);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (InvalidKeySpecException e) {
            log.error("", e);
        } finally {
            if (publicKey != null) {
                try {
                    publicKey.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
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
