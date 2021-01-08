package com.ljm.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author Created by liangjiaming on 2021/1/6
 * @title
 * @Desc
 */
public class App {
    public static void main(String[] args) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println(time);

        File file = new File("");
        if (file.exists()) {
            System.out.println("文件存在");
        } else {
            System.out.println("文件不存在");
        }

    }
}
