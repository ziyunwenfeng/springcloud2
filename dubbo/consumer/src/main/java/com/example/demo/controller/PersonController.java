package com.example.demo.controller;

import com.example.common.entity.Person;
import com.example.common.interfaces.IPersonInterface;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dubbo
 * @description: person jcontroller
 * @author: wenfeng
 * @create: 2019-07-11 10:37
 **/
@RestController
public class PersonController {
    @Reference(check = false, mock = "true", timeout = 3000, version = "1.0.2")
    IPersonInterface person;
    @GetMapping("/get")
    public Person get(){
        return person.getPerson();
    }
}
