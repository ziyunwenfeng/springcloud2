package com.example.common.interfaces;

import com.example.common.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class IPersonInterfaceMock implements IPersonInterface{
    public IPersonInterfaceMock(){}

    @Override
    public Person getPerson() {
        return new Person(2,"mock");
    }
}
