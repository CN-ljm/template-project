package com.ljm.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

/**
 * @author Created by liangjiaming on 2021/1/6
 * @title
 * @Desc
 */
public class App {
    public static void main(String[] args) throws JsonProcessingException {
        /*String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println(time);

        File file = new File("");
        if (file.exists()) {
            System.out.println("文件存在");
        } else {
            System.out.println("文件不存在");
        }*/

        String aa = new String("AAA");
        ObjectMapper mapper = new ObjectMapper();
        String s1 = mapper.writeValueAsString(aa);
        Map<String, Object> map = mapper.readValue(s1, Map.class);

        System.out.println(map);

    }
}
