package com.example.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @program: mq
 * @description: mq producer
 * @author: wenfeng
 * @create: 2019-06-27 15:27
 **/
@Slf4j
//@Configuration
@SpringBootConfiguration
public class Producer {
    @Autowired
    private ProducerConfig producerConfig;
    @Bean
    public DefaultMQProducer getDefaultMQProducer(){
        log.info(producerConfig.toString());
        log.info("rocketmq producer server starting-----------");
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            log.info("rocketmq producer server stared-----------");
            return producer;
        } catch (MQClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
