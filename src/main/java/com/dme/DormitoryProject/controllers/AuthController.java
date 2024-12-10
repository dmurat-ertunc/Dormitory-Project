package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.ITokenBlackListService;
import com.dme.DormitoryProject.business.services.IUserService;
import com.dme.DormitoryProject.dtos.auth.AuthResponseDTO;
import com.dme.DormitoryProject.dtos.auth.PasswordChangeDTO;
import com.dme.DormitoryProject.dtos.login.LoginDTO;
import com.dme.DormitoryProject.repository.IRoleDao;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.security.CustomUserDetailService;
import com.dme.DormitoryProject.security.JWTGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private IUserDao userDao;
    private IRoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private ITokenBlackListService tokenBlackListService;
    private IUserService userService;
    private CustomUserDetailService customUserDetailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserDao userDao,
                          IRoleDao roleDao, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,
                          ITokenBlackListService tokenBlackListService, IUserService userService,
                          CustomUserDetailService customUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.tokenBlackListService=tokenBlackListService;
        this.userService=userService;
        this.customUserDetailService=customUserDetailService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDto.getUserName());
        // COOKIE OLUŞTURMA
//        Cookie jwtCookie = new Cookie("jwtToken",token);
//        jwtCookie.setHttpOnly(true); // JavaScript tarafından erişim engellenir
//        jwtCookie.setSecure(true); // HTTPS kullanıyorsanız etkinleştirin
//        jwtCookie.setMaxAge(3600000); // Süreyi belirleyin (saniye cinsinden)
//        jwtCookie.setPath("/"); // Cookie'nin erişilebilir olduğu yol
        return new ResponseEntity<>(new AuthResponseDTO(token,userDetails.getUsername(),userDetails.getAuthorities()), HttpStatus.OK);
    }
    @PostMapping("passwordChange")
    public Result passwordChange(@RequestBody PasswordChangeDTO passwordChangeDTO){
        return this.userService.passwordChangeDormitoryUser(passwordChangeDTO);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        tokenBlackListService.blackListToken(jwt);
        return ResponseEntity.ok("Çıkış Başarılı");
    }

    @GetMapping("tokenDecoder/{token}")
    public Result tokenDecoder(@PathVariable String token){
        String bearerToken = token;

        // Token'ı '.' ile ayır
        String[] tokenParts = bearerToken.split("\\.");
        if (tokenParts.length < 2) {
            throw new IllegalArgumentException("Geçersiz JWT Token");
        }

        // Payload kısmını Base64URL decode et
        String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]));

        // JSON string'i Map olarak oku
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, Object> payloadMap = objectMapper.readValue(payload, Map.class);
            System.out.println("Payload: " + payloadMap);
            return new SuccessDataResult("Token içeriği açıldı",true,payloadMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResult("hata var",false);
        }

    }
}
