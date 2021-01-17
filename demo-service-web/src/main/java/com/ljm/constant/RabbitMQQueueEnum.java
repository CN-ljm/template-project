package com.ljm.constant;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Created by liangjiaming on 2021/1/8
 * @title 通用的rabbitmq消息定义
 * @Desc
 */
public enum RabbitMQQueueEnum {

    AUTO_QUEUE("auto.queue", "auto.queue.key", "direct.test", "测试自动创建队列");


    /**
     * 队列名（不能为空）
     */
    private String queueName;

    /**
     * 路由值（不能为空）
     */
    private String routingKey;

    /**
     * 交换机，为空就取默认本服务默认交换机
     */
    private String exchange;

    /**
     * 说明
     */
    private String desc;

    RabbitMQQueueEnum() {
    }

    RabbitMQQueueEnum(@NotEmpty String queueName, @NotEmpty String routingKey, @Nullable String exchange, String desc) {
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchange = exchange;
        this.desc = desc;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
