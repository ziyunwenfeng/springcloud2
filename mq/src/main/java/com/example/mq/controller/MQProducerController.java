package com.example.mq.controller;

import com.alibaba.fastjson.JSON;
import com.example.mq.entity.User;
import com.example.mq.rocketmq.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mq
 * @description: mq controller
 * @author: wenfeng
 * @create: 2019-06-27 16:11
 **/
@Slf4j
@RestController
@RequestMapping("/mq")
public class MQProducerController {
    @Autowired
    SendMessage sendMessage;
    private DefaultMQProducer defaultMQProducer;
    @PostMapping("/test")
    public void test() throws Exception{
        User user = new User("yy");
        sendMessage.send("TopicTest", "Tag1",user);
    }

}
