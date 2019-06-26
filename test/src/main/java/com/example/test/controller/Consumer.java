package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @program: test
 * @description: this is consumer
 * @author: wenfeng
 * @create: 2019-06-25 19:37
 **/
@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "hellotest")
    public void listen(ConsumerRecord<?,?> record){
        log.info("consumer:---");
        System.out.println(record.topic());
        System.out.println(record.offset());
        System.out.println(record.value());
    }
}
