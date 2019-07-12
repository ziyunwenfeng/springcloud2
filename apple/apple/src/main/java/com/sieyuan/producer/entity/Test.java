package com.sieyuan.producer.entity;

import java.io.Serializable;

/**
* Author wenfeng
* Date  2019-07-09
*/
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String test;


    public Test(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setTest (String test) {this.test = test;} 
    public String getTest(){ return test;} 

}