package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ITokenBlackListService;
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
