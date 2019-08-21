package com.example.demo.service;

import com.example.common.entity.Person;
import com.example.common.interfaces.IPersonInterface;
import org.apache.dubbo.config.annotation.Service;

/**
 * @program: dubbo
 * @description: person service
 * @author: wenfeng
 * @create: 2019-07-11 10:12
 **/
@Service
public class PersonServiceImp implements IPersonInterface {
    @Override
    public Person getPerson() {
        return new Person(1,"web");
    }
}
