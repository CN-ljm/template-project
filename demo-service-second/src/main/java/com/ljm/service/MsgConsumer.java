package com.ljm.service;

import com.alibaba.fastjson.JSON;
import com.ljm.base.utils.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author Created by liangjiaming on 2021/1/6
 * @title
 * @Desc
 */
@Service
@Slf4j
public class MsgConsumer {

    @RabbitListener(queues = "test")
    @RabbitHandler
    public void consumerMsg(Message msg) throws InterruptedException, UnsupportedEncodingException {
        log.info("收到消息，消息内容是：{}", new String(msg.getBody(), "utf-8"));
        log.info("收到消息，消息配置是：{}", JSON.toJSONString(msg.getMessageProperties()));
        Thread.currentThread().sleep(1000*10);

    }

    /*@RabbitListener(queues = "deadLetter.test")
    @RabbitHandler
    public void consumerDeadLetterMsg(Message msg) throws UnsupportedEncodingException {
        String msgBody = new String(msg.getBody(), "utf-8");
        System.out.println("死信消息，消息内容是：" + msgBody);
        System.out.println("死信消息，消息配置是：" + JSON.toJSONString(msg.getMessageProperties()));
    }*/

}
