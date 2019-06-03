package com.sieyuan.stream_consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }
}
