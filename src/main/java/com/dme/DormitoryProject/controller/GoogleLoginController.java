package com.dme.DormitoryProject.controller;

import com.dme.DormitoryProject.business.services.IGoogleLoginService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GoogleLoginController {
    private IGoogleLoginService googleLoginService;

    @Autowired
    public GoogleLoginController(IGoogleLoginService googleLoginService){
        this.googleLoginService=googleLoginService;
    }

    @GetMapping
    public Result loginSuccess(Principal principal){
        return this.googleLoginService.loginGoogleUser(principal);
    }

}
