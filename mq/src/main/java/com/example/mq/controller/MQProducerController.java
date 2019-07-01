//package com.example.mq.controller;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @program: mq
// * @description: mq controller
// * @author: wenfeng
// * @create: 2019-06-27 16:11
// **/
//@Slf4j
//@RestController
//@RequestMapping("/mq")
//public class MQProducerController {
//    @Autowired
//    private DefaultMQProducer defaultMQProducer;
//    @PostMapping("/test")
//    public void test() throws Exception{
//        Message message = new Message("TopicTest", "TagA", "rocketmq测试".getBytes());
//        defaultMQProducer.send(message, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info("success");
//                log.info(JSON.toJSONString(sendResult));
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                log.info("fail");
//            }
//        });
//    }
//
//}
