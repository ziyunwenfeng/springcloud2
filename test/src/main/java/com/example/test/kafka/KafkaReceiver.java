package com.example.test.kafka;

import com.alibaba.fastjson.JSON;
import com.example.test.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @program: test
 * @description: kafka receive Object
 * @author: wenfeng
 * @create: 2019-06-26 16:19
 **/
@Component
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = TopicConst.HELLOTEST)
    public void listen(ConsumerRecord<?,?> record){
        log.info("consumer:---");
        System.out.println(record.topic());
        System.out.println(record.offset());
        System.out.println(record.value());
        User u = (User)record.value();
        System.out.println(u.getUsername());
    }

}
