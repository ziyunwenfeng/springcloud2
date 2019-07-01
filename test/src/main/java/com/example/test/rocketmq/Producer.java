package com.example.test.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: test
 * @description: mq producer
 * @author: wenfeng
 * @create: 2019-06-27 15:27
 **/
@Slf4j
@Configuration
public class Producer {
    @Autowired
    private ProducerConfig producerConfig;
    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.producer",value = "default",havingValue = "false")
    public DefaultMQProducer defaultMQProducer() throws MQClientException{
        log.info(producerConfig.toString());
        log.info("rocketmq producer server starting-----------");
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server stared-----------");
        return producer;
    }
}
