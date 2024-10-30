package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.dtos.auth.AuthResponseDTO;
import com.dme.DormitoryProject.dtos.login.LoginDTO;
import com.dme.DormitoryProject.dtos.register.RegisterDTO;
import com.dme.DormitoryProject.entity.Roles;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.repository.IRoleDao;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private IUserDao userDao;
    private IRoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserDao userDao,
                          IRoleDao roleDao, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (userDao.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Kullanıcı adı zaten alınmış", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsernName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Roles roles = roleDao.findByName("STAFF").get();
        user.setRoles(Collections.singletonList(roles));

        userDao.save(user);

        return new ResponseEntity<>("Kayıt ekleme başarılı", HttpStatus.OK);
    }
}
