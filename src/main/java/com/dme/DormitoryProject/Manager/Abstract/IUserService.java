package com.dme.DormitoryProject.Manager.Abstract;

public interface IUserService<T> {
    void saveUser(Object dto, String role,String password);
}
