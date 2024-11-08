package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.response.Result;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public interface IGoogleLoginService {
    Result loginGoogleUser(Principal principal);
}
