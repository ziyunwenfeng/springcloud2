package com.example.mq.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    public User(){
        super();
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
