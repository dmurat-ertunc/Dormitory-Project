package com.dme.DormitoryProject.dtos.auth;

public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
    public AuthResponseDTO(){

    }
}
