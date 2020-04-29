package com.ljm.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Created by liangjiaming on 2020/4/23
 * @title http请求工具类
 * @Desc
 */
@Slf4j
public class HttpClientUtil {

    /**
     * Get请求
     * @param httpUrl 请求路径
     * @param param 请求参数 形式：k=v&k2=v2...
     * @return 返回结果
     */
    public static String doGet(String httpUrl, Map<String, String> param) throws UnsupportedEncodingException {
        log.info("请求路径【{}】请求参数【{}】", httpUrl, JSONObject.toJSONString(param));
        HttpURLConnection connection = null;
        InputStream is = null;
        String res = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder requestParam = new StringBuilder();
        for (Map.Entry<String, String> set: param.entrySet()){
            String value = URLEncoder.encode(set.getValue(), "UTF-8");
            requestParam.append(set.getKey())
                    .append("=")
                    .append(value)
                    .append("&");
        }
        String urlParam = requestParam.substring(0, requestParam.lastIndexOf("&"));
        try {
            String realUrl = httpUrl+"?"+urlParam;
            System.out.println("请求路径：" + realUrl);
            URL url = new URL(realUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(6000);
            connection.setRequestProperty("character", "UTF-8");
            connection.setReadTimeout(6000);
            //请求
            connection.connect();

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null){
                    sb.append(temp);
                    sb.append("\r\n");
                }
            }
            res = sb.toString();
            log.info("返回结果【{}】", res);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭链接
            connection.disconnect();
        }
        return res;
    }

    /**
     * Post请求
     * @param httpUrl 请求路径
     * @param param 请求参数
     * @return 返回结果
     */
    public static String doPost(String httpUrl, Map<String, Object> param){
        log.info("请求路径【{}】请求参数【{}】", httpUrl, JSONObject.toJSONString(param));
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        String res = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            String realUrl = httpUrl;
            URL url = new URL(realUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();
            os = connection.getOutputStream();
            //请求
            os.write(JSONObject.toJSONString(param).getBytes("UTF-8"));
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null){
                    sb.append(temp);
                    sb.append("\r\n");
                }
            }
            res = sb.toString();
            log.info("返回结果【{}】", res);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭链接
            connection.disconnect();
        }
        return res;
    }

}
