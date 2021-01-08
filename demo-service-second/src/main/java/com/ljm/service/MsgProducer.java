package com.ljm.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author Created by liangjiaming on 2021/1/6
 * @title
 * @Desc
 */
@Service
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) throws UnsupportedEncodingException {
        byte[] bytes = msg.getBytes("utf-8");
        rabbitTemplate.convertAndSend("direct.test","test.key", bytes);
    }

}
