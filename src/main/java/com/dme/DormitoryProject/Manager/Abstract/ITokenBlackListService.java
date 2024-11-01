package com.dme.DormitoryProject.Manager.Abstract;

public interface ITokenBlackListService {
    void blackListToken(String token);
    boolean isTokenBlackListed(String token);
}
