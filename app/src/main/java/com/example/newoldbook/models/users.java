package com.example.newoldbook.models;

public class users {
    String   username,mail,password,userid;

    public users(String username, String mail, String password, String userid) {

        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userid = userid;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public users(){}
//    signup constructor

    public users( String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getUserid(String key) {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }


}
