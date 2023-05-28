package com.example.newoldbook.models;

public class bookmodel {

String Profilepic, UID,subjectn,bemail,phone,price;

    public bookmodel( String  Profilepic,String UID,String subjectn, String bemail, String phone, String price) {

        this.UID=UID;
        this.subjectn = subjectn;
        this.bemail = bemail;
        this.phone = phone;
        this.price = price;
        this.Profilepic=Profilepic;

    }

     public bookmodel(){}

    public bookmodel(String UID,String subjectn, String bemail, String phone, String price){
        this.UID=UID;
        this.subjectn = subjectn;
        this.bemail = bemail;
        this.phone = phone;
        this.price = price;
    }

    public String getProfilepic() {
        return Profilepic;
    }

    public void setProfilepic(String profilepic) {
        Profilepic = profilepic;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSubjectn() {
        return subjectn;
    }

    public void setSubjectn(String subjectn) {
        this.subjectn = subjectn;
    }

    public String getBemail() {
        return bemail;
    }

    public void setBemail(String bemail) {
        this.bemail = bemail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
