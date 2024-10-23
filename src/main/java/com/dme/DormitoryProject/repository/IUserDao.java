package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<User,Long> {
    Optional<User> findByMail(String mail);
    Boolean existsByMail(String mail);
}
