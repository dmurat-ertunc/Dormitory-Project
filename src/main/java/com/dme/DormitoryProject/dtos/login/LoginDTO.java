package com.dme.DormitoryProject.dtos.login;

public class LoginDTO {
    private String userName;
    private String password;

    public LoginDTO() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
