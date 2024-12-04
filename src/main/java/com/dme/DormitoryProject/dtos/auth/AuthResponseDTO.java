package com.dme.DormitoryProject.dtos.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthResponseDTO {
    private String accessToken;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String tokenType = "Bearer";

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private Collection<? extends GrantedAuthority> roles;

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public AuthResponseDTO(String accessToken, String userName,
                           Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.userName=userName;
        this.roles=authorities;
    }
    public AuthResponseDTO(){

    }
}
