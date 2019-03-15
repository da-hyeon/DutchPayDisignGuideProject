package com.example.mp_3.dutchpaydisignguideproject.Model;

public class User {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhoneNumber;
    private String userDutchMoney;

    private User(){
        //임시 데이터 등록
        userEmail = "kjg123kg@dutch-pay.com";
        userPassword = "dutchpay";
        userName = "황다현";
        userPhoneNumber = "010-7600-1318";
        userDutchMoney = "54216514";
    }

    private static class UserHolder {
        public static final User ourInstance = new User();
    }

    public static User getInstance() {
        return UserHolder.ourInstance;
    }


    public User(String userEmail, String userPassword, String userName, String userPhoneNumber, String userDutchMoney) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userDutchMoney = userDutchMoney;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserDutchMoney() {
        return userDutchMoney;
    }

    public void setUserDutchMoney(String userDutchMoney) {
        this.userDutchMoney = userDutchMoney;
    }
}
