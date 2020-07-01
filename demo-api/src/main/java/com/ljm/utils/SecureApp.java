package com.ljm.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Created by liangjiaming on 2020/4/29
 * @title
 * @Desc
 */
public class SecureApp {
    public static void main(String[] args) throws IOException {
//        Sha1withRSAUtil.generatorKeyPair("D:/key");

        String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA49knPUWdQbZVBUZ/7MyY1XcwKJPSec6snA2QXKtcI4SwUCMaMO3lg03n948ffwBw1rHINwnjUQPe1El0uQrzpwIDAQABAkBCNYP1hhkgUz95fPcHdJykyzGvWOU8iBYYcsiUZSctGoFSr1dKn34ly+o1OnoxGksS2NHqgcNSbXhx8XgzywEBAiEA9Btkw1og/2bscWkPzAmhy13IenrHoY0GcUWkh/0wHQcCIQDu8vk+q3pZ6iGxCxLhGEl1Yuu2iubuNEBjFHPbgqdsYQIgIMQwkVxew4mwQUTl/vBoDGFJUM98Tn4cmEXN4QcmVy8CIAzr5r6U11D1cEz6pFK31YEbkFOWv1YyTSkOehVsw1EBAiA/P/fv6pDUTIySH5wo/WvlAng/IBxZYPYe/8Wqaf+pVw==";
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOPZJz1FnUG2VQVGf+zMmNV3MCiT0nnOrJwNkFyrXCOEsFAjGjDt5YNN5/ePH38AcNaxyDcJ41ED3tRJdLkK86cCAwEAAQ==";

        byte[] data = "name=aaa".getBytes();
        //加签 Sha1withRSAUtil.getPrivateKey("D:/key/private.txt")
        String sign = Sha1withRSAUtil.sign(data, privateKey);
        System.out.println("签名是：" + sign);
        //验签 Sha1withRSAUtil.getPublicKey("D:/key/public.txt")
        boolean verify = Sha1withRSAUtil.verify(data, sign, publicKey);
        System.out.println("验签结果是：" + verify);
    }
}
