package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.kafka.KafkaSender;
import com.example.test.kafka.TopicConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private KafkaSender<Object> kafkaSender;

    @PostMapping("/send")
    public String send(@RequestParam("msg") String msg){
        kafkaSender.send(msg,TopicConst.HELLOTEST);
        return "success";
    }
    @PostMapping("/send/user")
    public void sendUser(@RequestBody User user){
        kafkaSender.send(user,TopicConst.HELLOTEST);
    }
}
