package com.sieyuan.stream_producer.controller;

import com.sieyuan.stream_producer.service.ProducerStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private ProducerStreamService service;
    @GetMapping("/sendMessageByChannel")
    public void sendMessage(@RequestParam("channel") String channel){
       service.sendMessage(channel);
    }
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

}
