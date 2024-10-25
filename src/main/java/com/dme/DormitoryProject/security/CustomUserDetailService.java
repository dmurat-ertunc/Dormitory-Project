package com.dme.DormitoryProject.security;

import com.dme.DormitoryProject.entity.Roles;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.repository.IUserDao;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailService implements UserDetailsService {
    private IUserDao userDao;

    @Autowired
    public CustomUserDetailService(IUserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userDao.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı adı bulunamadı"));
        return new org.springframework.security.core.userdetails.User(user.getUsernName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
