package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IUserService;
import com.dme.DormitoryProject.dtos.auth.PasswordChangeDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Roles;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.enums.UserType;
import com.dme.DormitoryProject.repository.IRoleDao;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.security.CustomUserDetailService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManager implements IUserService<T> {

    @Autowired
    private CustomUserDetailService customUserDetailsService;
    private IUserDao userDao;
    private IRoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserManager(IUserDao userDao, IRoleDao roleDao, PasswordEncoder passwordEncoder){
        this.userDao=userDao;
        this.roleDao=roleDao;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public void saveDormitoryUser(Object dto, String role,String password,String name, String surName) {
        User user = new User();
        String username = mailConvertToUsername(findDto(dto));

        user.setUsernName(username);
        user.setName(name);
        user.setSurName(surName);
        user.setUserType(UserType.Dormitory_User);
        user.setPassword(passwordEncoder.encode(password));
        user.setMail(findDto(dto));
        Roles roles = roleDao.findByName(role).get();
        user.setRoles(Collections.singletonList(roles));

        userDao.save(user);
    }

    @Override
    public void saveGoogleUser(String name, String surName,String mail) {
        User user = new User();
        List<User> userList = userDao.findAll();
        Random random = new Random();
        int pswrd = 0;
        boolean isUnique = false;

        while (!isUnique) {
            pswrd = 10000 + random.nextInt(90000);
            isUnique = true;
            for (User user1 : userList) {
                if (Objects.equals(user.getPassword(), pswrd)) {
                    isUnique = false;
                    break;
                }
            }
        }
        user.setPassword(passwordEncoder.encode(Integer.toString(pswrd)));
        user.setMail(mail);
        user.setUserType(UserType.Google_User);
        user.setName(name);
        user.setSurName(surName);
        user.setUsernName(mailConvertToUsername(mail));
        Roles roles = roleDao.findByName("ROLE_DEFAULT").get();
        user.setRoles(Collections.singletonList(roles));

        userDao.save(user);

    }

    @Override
    public void deleteDormitoryUser(String mail){
        List<User> userList = userDao.findAll();
        for (User user : userList){
            if (Objects.equals(user.getMail(),mail)){
                userDao.delete(user);
            }
        }
    }

    @Override
    public void updateDormitoryUser(Map<String,String> updateUser){
        String mail = updateUser.get("mail");
        String name = updateUser.get("name");
        String surName = updateUser.get("surName");

        User user = userDao.findUserByEmail(mail);
        user.setName(name);
        user.setSurName(surName);
        user.setMail(mail);
        user.setUsernName(mailConvertToUsername(mail));

    }

    @Override
    public Result passwordChangeDormitoryUser(PasswordChangeDTO passwordChangeDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) { // CustomUserDetails, UserDetails'in bir özelleştirilmiş hali olmalı
            String username = ((UserDetails) principal).getUsername(); // Username değerini al
            User user = userDao.findUserByUsername(username); // hibernate sql ile username değerine göre user ı al
            if (!passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword())){
                return new ErrorResult("Eski şifreniz yanlış",false);
            }
            if (!Objects.equals(passwordChangeDTO.getNewPassword(),passwordChangeDTO.getNewPasswordAgain())){
                return new ErrorResult("Yeni şifreler uyuşmuyor",false);
            }
            if (Objects.equals(passwordChangeDTO.getNewPassword(),passwordChangeDTO.getOldPassword())){
                return new ErrorResult("Eski şifre ile yeni şifre aynı olamaz",false);
            }
            user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
            userDao.save(user);
        }
        else {
            return new ErrorResult("Aktif kullanıcı bulunamadı",false);
        }
        return new SuccesResult("Şifre güncelleme işlemi başarılı",true);
    }

    private String mailConvertToUsername(String mail) {
        if (mail == null || !mail.contains("@")) {
            throw new IllegalArgumentException("Geçersiz e-posta adresi");
        }
        String username = mail.split("@")[0];
        String formattedUsername = username.replace(".", "");
        String domain = mail.split("@")[1].split("\\.")[0]; // ".com" veya diğer uzantılardan önceki kısmı alır
        String formattedEmail = formattedUsername + domain;

        return formattedEmail;
    }

    private  <T> String findDto(T dto){
        if (dto instanceof ManagerDTO) {
            return ((ManagerDTO) dto).getMail();
        } else if (dto instanceof StaffDTO) {
            return ((StaffDTO) dto).getMail();
        } else if (dto instanceof StudentDTO) {
            return ((StudentDTO) dto).getMail();
        } else {
            throw new IllegalArgumentException("Geçersiz tür");
        }
    }
}
