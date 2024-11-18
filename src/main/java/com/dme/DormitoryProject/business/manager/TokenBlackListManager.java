package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.ITokenBlackListService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlackListManager implements ITokenBlackListService {
    private final Set<String> blackListedTokens = ConcurrentHashMap.newKeySet();

    public void blackListToken(String token) {
        blackListedTokens.add(token);
    }

    public boolean isTokenBlackListed(String token) {
        return blackListedTokens.contains(token);
    }
}
