package com.sieyuan.producer.entity;

import java.io.Serializable;
import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
*/
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id; 
    private String nickname; 
    private List<Idcard> idcards; 


    public Person(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setNickname (String nickname) {this.nickname = nickname;} 
    public String getNickname(){ return nickname;} 
    public void setIdcards (List<Idcard> idcards) {this.idcards = idcards;} 
    public List<Idcard> getIdcards(){ return this.idcards;} 

}