package com.example.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: mq
 * @description: mq producer
 * @author: wenfeng
 * @create: 2019-06-27 15:27
 **/
@Slf4j
@SpringBootConfiguration
public class Consumer {
    @Autowired
    private ConsumerConfig consumerConfig;
    @Autowired
    private IMessageHandler messageHandler;
    /*
     * description: 消费者监听
     * @param topics
     * @param tag
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/6/27 15:45
     */
    @Bean
    public DefaultMQPushConsumer getConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
        consumer.setNamesrvAddr(consumer.getNamesrvAddr());
        MessageListener listener = new MessageListener();
        listener.setMessageHandler(messageHandler);
        consumer.registerMessageListener(listener);
        try {
            consumer.subscribe(consumerConfig.getTopic(),"*");
            consumer.start();
            log.info("consumer stared success-----------------");
        } catch (MQClientException e) {
            e.printStackTrace();
            log.info("stared fail-----------------");
            return null;
        }
        return consumer;
    }

}
