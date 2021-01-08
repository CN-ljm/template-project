package com.ljm.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by liangjiaming on 2021/1/6
 * @title
 * @Desc
 */
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitMQConfig {

    /**
     * 声明一个队列，并绑定对应死信队列
     * @return
     */
    @Bean
    public Queue testQueue() {
        System.out.println("添加队列");
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "direct.deadLetter.test");
        args.put("x-dead-letter-routing-key", "deal.key");
        args.put("x-message-ttl", 2000);
        return new Queue("test", true, false, false, args);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("deadLetter.test");
    }

    /**
     * 声明一个交换机
     * @return
     */
    @Bean
    public Exchange testDirectExchange() {
        System.out.println("添加交换器");
        return new DirectExchange("direct.test");
    }

    @Bean
    public Exchange dealLetterDirectExchange() {
        return new DirectExchange("direct.deadLetter.test");
    }

    /**
     * 创建绑定关系
     * @return
     */
    @Bean
    public Binding testBindingDirect() {
        System.out.println("添加绑定关系");
        return BindingBuilder.bind(testQueue()).to(testDirectExchange()).with("test.key").noargs();
    }

    @Bean
    public Binding dealLetterBindingDirect() {
        return BindingBuilder.bind(deadLetterQueue()).to(dealLetterDirectExchange()).with("deal.key").noargs();
    }

}
