package com.example.mp_3.dutchpaydisignguideproject.data.db.model;

public class User {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPaymentPassword;
    private String userPhoneNumber;
    private String userDutchMoney;
    private boolean userState;

    private User(){
        //임시 데이터 등록
        userEmail = "asd";
        userPassword = "asd";
        userName = "황다현";
        userPaymentPassword = "123456";
        userPhoneNumber = "010-7600-1318";
        userDutchMoney = "54216514";
    }

    private static class UserHolder {
        public static final User ourInstance = new User();
    }

    public static User getInstance() {
        return UserHolder.ourInstance;
    }

    public User(String userEmail, String userPassword, String userName, String userPaymentPassword, String userPhoneNumber, String userDutchMoney) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPaymentPassword = userPaymentPassword;
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

    public String getUserPaymentPassword() {
        return userPaymentPassword;
    }

    public void setUserPaymentPassword(String userPaymentPassword) {
        this.userPaymentPassword = userPaymentPassword;
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

    public boolean isUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }
}
