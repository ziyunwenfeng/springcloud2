package entity;

import java.io.Serializable;
import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
*/
public class Idcard implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String idname;
    private int personId;


    public Idcard(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setIdname (String idname) {this.idname = idname;} 
    public String getIdname(){ return idname;} 
    public void setPersonId (int personId) {this.personId = personId;} 
    public int getPersonId(){ return personId;} 

}