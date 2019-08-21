package com.example.common.entity;

import java.io.Serializable;

/**
 * @program: dubbo
 * @description: person
 * @author: wenfeng
 * @create: 2019-07-11 09:38
 **/

public class Person implements Serializable {
    private static final long serialVersionUID = 1415852192397895853L;
    int id;
    String name;

    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
