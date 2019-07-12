package com.sieyuan.producer.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int count;

    public Product() {

    }

    public Product(String name, int count) {
        this.name = name;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
