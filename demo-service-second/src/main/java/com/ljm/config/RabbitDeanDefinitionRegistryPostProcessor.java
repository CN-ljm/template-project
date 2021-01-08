package com.ljm.config;

import com.ljm.constant.RabbitMQQueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by liangjiaming on 2021/1/8
 * @title
 * @Desc
 */
@Configuration
@Slf4j
public class RabbitDeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Queue.class).getBeanDefinition();
        for (RabbitMQQueueEnum queue: RabbitMQQueueEnum.values()) {
            beanDefinition.getPropertyValues().addPropertyValue("name", queue.getQueueName());
            registry.registerBeanDefinition("aaa", beanDefinition);
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
