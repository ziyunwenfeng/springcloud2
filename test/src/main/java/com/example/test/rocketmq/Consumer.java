package com.example.test.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @program: test
 * @description: mq producer
 * @author: wenfeng
 * @create: 2019-06-27 15:27
 **/
@Slf4j
@Configuration
public abstract class Consumer {
    @Autowired
    private ConsumerConfig consumerConfig;

    /*
     * description: 消费者监听
     * @param topics
     * @param tag
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/6/27 15:45
     */
    public void listener(String topics,String tag) throws MQClientException{
        log.info("consumer-----------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
        consumer.setNamesrvAddr(consumer.getNamesrvAddr());
        consumer.subscribe(topics,tag);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return Consumer.this.dealBody(msgs);
            }
        });
        consumer.start();
        log.info("stared success-----------------");
    }
    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);
}
