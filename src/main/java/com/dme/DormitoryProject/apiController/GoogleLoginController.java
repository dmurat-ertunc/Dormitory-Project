package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IGoogleLoginService;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.security.CustomUserDetailService;
import com.dme.DormitoryProject.security.JWTGenerator;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

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
