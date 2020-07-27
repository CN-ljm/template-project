package com.ljm.utils;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Created by liangjiaming on 2020/4/9
 * @title
 * @Desc
 */
public class App {
    public static void main(String[] args) throws Exception {

        /*String imagePath = "aaa/bbb.jpg";
        boolean isManaualCheck = false;
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String suffix = imagePath.substring(imagePath.lastIndexOf("."));
        String imageFileName = "_" + date + "_" + (int)((Math.random()*9+1)*1000) + suffix;
        imageFileName = isManaualCheck ? "M"+imageFileName : "A"+imageFileName;
        String fileName = imageFileName;
        LocalDate bTime = LocalDate.parse(fileName.substring(2,10), DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(date);
        System.out.println(imageFileName);
        System.out.println(bTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));*/

        /*String aa = "aaa.jpg";
        String substring = aa.substring(aa.lastIndexOf("."));
        System.out.println(substring);
        String type = substring.substring(1);
        System.out.println(type);*/

        LocalDate date1 = LocalDate.of(2020, 6, 2);
        long interval = date1.toEpochDay() - LocalDate.now().toEpochDay();
        System.out.println(interval);

        BigDecimal aa = BigDecimal.ZERO;
        BigDecimal bb = new BigDecimal(11.11);
        System.out.println(aa.add(bb));

    }
}


@Builder
@Data
class Person implements Serializable {
    private String name;
    private Integer age = 0;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
