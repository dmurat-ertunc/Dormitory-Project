package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.auth.PasswordChangeDTO;
import com.dme.DormitoryProject.response.Result;

import java.util.Map;

public interface IUserService<T> {
    void saveDormitoryUser(Object dto, String role,String password,String name, String surName);
    void saveGoogleUser(String name, String surName,String mail);
    void deleteDormitoryUser(String mail);
    void updateDormitoryUser(Map<String,String> updateUser);
    Result passwordChangeDormitoryUser(PasswordChangeDTO passwordChangeDTO);
}
