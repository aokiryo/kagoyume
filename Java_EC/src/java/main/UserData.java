/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ryo
 */
public class UserData implements Serializable{
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private int deleteFlg;
    
    public UserData(){
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
//        this.total= 0;
//        this.deleteFlg = 1;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
            this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
            this.password = password;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
            this.mail = mail;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
            this.address = address;
    }

//    public int getTotal() {
//        return total;
//    }
//    public void setTotal(int total) {
//            this.total = total;
//    }
//
//    public int getDeleteFlg() {
//        return deleteFlg;
//    }
//    public void setDeleteFlg(int deleteFlg) {
//            this.deleteFlg = deleteFlg;
//        }


    
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        
        return chkList;
    }

    public void UD2DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
//        udd.setTotal(this.total);
//        udd.setDeleteFlg(this.deleteFlg);
    }
    
}
