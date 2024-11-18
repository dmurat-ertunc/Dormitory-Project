package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.response.Result;

import java.security.Principal;

public interface IGoogleLoginService {
    Result loginGoogleUser(Principal principal);
}
