package com.sieyuan.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: auth-service
 * @description: test Controller
 * @author: wenfeng
 * @create: 2019-06-16 23:40
 **/
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
