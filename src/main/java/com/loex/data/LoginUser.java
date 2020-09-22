package com.loex.data;

public class LoginUser {
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoginPword() {
        return loginPword;
    }

    public void setLoginPword(String loginPword) {
        this.loginPword = loginPword;
    }

    String mobileNumber;
    String loginPword;


    public LoginUser(String mobileNumber, String loginPword) {
        this.mobileNumber = mobileNumber;
        this.loginPword = loginPword;
    }
    public LoginUser(){
        super();
    }




}
