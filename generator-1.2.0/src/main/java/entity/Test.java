package entity;

import java.io.Serializable;
import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
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