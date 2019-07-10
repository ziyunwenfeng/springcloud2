package entity;

import java.io.Serializable;
import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
public class Org implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String test;
    private List<Cou> cous; 


    public Org(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setTest (String test) {this.test = test;} 
    public String getTest(){ return test;} 
    public void setCous (List<Cou> cous) { 
 this.cous = cous;
} 
    public List<Cou> getCous(){ return this.cous;} 

}