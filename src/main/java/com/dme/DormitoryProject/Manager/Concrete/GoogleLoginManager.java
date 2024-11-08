package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IGoogleLoginService;
import com.dme.DormitoryProject.Manager.Abstract.IUserService;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Service
public class GoogleLoginManager implements IGoogleLoginService {
    private IUserDao userDao;
    private IUserService userService;

    @Autowired
    public GoogleLoginManager(IUserDao userDao, IUserService userService){
        this.userDao=userDao;
        this.userService=userService;
    }

    @Override
    public Result loginGoogleUser(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = oauthToken.getPrincipal();
            String email = (String) oauthUser.getAttribute("email");
            String temporaryName = (String) oauthUser.getAttribute("name");
            String familyName = (String) oauthUser.getAttribute("family_name");
            String reelName = temporaryName.replace(familyName, "").trim();
            if (!(userDao.existsByMail(email))){
                try {
                    userService.saveGoogleUser(reelName,familyName,email);
                    return new SuccessDataResult("Hoşgeldiniz, ilk girişiniz başarılı",true,email);
                } catch (Exception e) {
                    return new ErrorResult("Bir hata oluştu",false);
                }
            }
            return new SuccessDataResult("Giriş başarılı",true,email);
        }
        return null;
    }
}
