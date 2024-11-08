package com.dme.DormitoryProject.Manager.Abstract;

public interface IUserService<T> {
    void saveDormitoryUser(Object dto, String role,String password,String name, String surName);
    void saveGoogleUser(String name, String surName,String mail);

}
