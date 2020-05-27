package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* Author wenfeng
* Date  2019-07-13
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String password;
    private String avatar; //头像
    private String province;
    private String city;
    private String area;
    private String address;
    private String phone;
    private String mail;
    private String isDelete;
}