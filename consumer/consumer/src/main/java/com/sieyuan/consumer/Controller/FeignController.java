package com.sieyuan.consumer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    ConsumerInterface consumerInterface;
    @RequestMapping(value="/consumer",method= RequestMethod.GET)
    public String consumerTest(){
        System.out.println("consumer");
        return consumerInterface.consumer();
    }
}
