package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IUserService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Roles;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.repository.IRoleDao;
import com.dme.DormitoryProject.repository.IUserDao;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UserManager implements IUserService<T> {

    private IUserDao userDao;
    private IRoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserManager(IUserDao userDao, IRoleDao roleDao, PasswordEncoder passwordEncoder){
        this.userDao=userDao;
        this.roleDao=roleDao;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public String mailToUsername(String mail) {
        Random random = new Random();
        List<User> userList = userDao.findAll();
        int value;
        boolean isUnique;
        do {
            value = 100000 + random.nextInt(900000);  // 100000-999999 arasında sayı üret
            int finalValue = value;
            isUnique = userList.stream().noneMatch(user -> user.getId().equals(finalValue));
        } while (!isUnique);
        String username = mail.split("@")[0];
        return "DMT" + value + username;
    }

    public <T> String findDto(T dto){
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

    @Override
    public void saveUser(T dto, String role,String password) {
        String username = mailToUsername(findDto(dto));

        User user = new User();
        user.setUsernName(username);
        user.setPassword(passwordEncoder.encode(password));

        Roles roles = roleDao.findByName(role).get();
        user.setRoles(Collections.singletonList(roles));

        userDao.save(user);
    }
}
