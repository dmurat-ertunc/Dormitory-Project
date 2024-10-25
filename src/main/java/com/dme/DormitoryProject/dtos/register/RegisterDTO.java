package com.dme.DormitoryProject.dtos.register;

public class RegisterDTO {
    private String userName;
    private String password;

    public RegisterDTO() {

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
