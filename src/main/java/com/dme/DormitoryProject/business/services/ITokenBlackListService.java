package com.dme.DormitoryProject.business.services;

public interface ITokenBlackListService {
    void blackListToken(String token);
    boolean isTokenBlackListed(String token);
}
