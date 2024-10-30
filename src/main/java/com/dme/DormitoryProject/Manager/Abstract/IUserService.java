package com.dme.DormitoryProject.Manager.Abstract;

public interface IUserService<T> {
    String mailToUsername(String mail);
    void saveUser(T dto, String role,String password);
}
