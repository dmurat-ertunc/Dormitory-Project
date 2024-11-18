package com.dme.DormitoryProject.Manager.Abstract;

import java.util.Map;

public interface IUserService<T> {
    void saveDormitoryUser(Object dto, String role,String password,String name, String surName);
    void saveGoogleUser(String name, String surName,String mail);
    void deleteDormitoryUser(String mail);
    void updateDormitoryUser(Map<String,String> updateUser);
}
