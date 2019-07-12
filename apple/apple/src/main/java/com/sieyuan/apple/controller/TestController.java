package com.sieyuan.apple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: producer
 * @description: test
 * @author: wenfeng
 * @create: 2019-07-12 10:17
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
