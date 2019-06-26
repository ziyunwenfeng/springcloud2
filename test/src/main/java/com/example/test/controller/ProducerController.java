package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: test
 * @description: test producer
 * @author: wenfeng
 * @create: 2019-06-25 19:32
 **/
@RestController
@RequestMapping("/kafka")
public class ProducerController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public String send(@RequestParam("msg") String msg){
        kafkaTemplate.send("hellotest",msg);
        return "success";
    }
}
