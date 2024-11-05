package com.dme.DormitoryProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private CustomUserDetailService customUserDetailService;
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService, JwtAuthEntryPoint jwtAuthEntryPoint){
        this.customUserDetailService=customUserDetailService;
        this.jwtAuthEntryPoint=jwtAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(auth -> auth
                        .requestMatchers("api/auth/**").permitAll() // `antMatchers` yerine `requestMatchers` kullanılıyor
                        .requestMatchers("api/departments/**").hasRole("MANAGER")
                        //.requestMatchers("api/managers/**").hasRole("MANAGER")
                        .requestMatchers("api/managers/**").permitAll()
                        .requestMatchers("api/rentals/**").hasAnyRole("STUDENT","STAFF","MANAGER")
                        .requestMatchers("api/sportAreas/**").hasAnyRole("STAFF","MANAGER")
                        //.requestMatchers("api/staffs/**").hasAnyRole("STAFF","MANAGER")
                        .requestMatchers("api/staffs/**").permitAll()
                        .requestMatchers("api/students/**").permitAll()
                        .requestMatchers("api/universities/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public  JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

//    @Bean
//    public UserDetailsService users(){
//
//    }
}
