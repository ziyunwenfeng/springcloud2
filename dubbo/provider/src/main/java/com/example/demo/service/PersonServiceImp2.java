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
@Service(version = "1.0.2")
public class PersonServiceImp2 implements IPersonInterface {
    @Override
    public Person getPerson() {
        return new Person(2,"web2");
    }
}
