package com.chen.app_ec.loginandregister.javabean;


public class UserLogin {
    private int userId;
    private String password;

    public UserLogin() {
    }

    public UserLogin(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
