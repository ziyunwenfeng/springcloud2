package entity;

import java.io.Serializable;
import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
public class Cou implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String test;
    private List<Org> orgs; 


    public Cou(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setTest (String test) {this.test = test;} 
    public String getTest(){ return test;} 
    public void setOrgs (List<Org> orgs) { 
 this.orgs = orgs;
} 
    public List<Org> getOrgs(){ return this.orgs;} 

}