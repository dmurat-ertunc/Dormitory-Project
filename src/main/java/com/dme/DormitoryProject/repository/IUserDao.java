package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserDao extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Optional<User> findByMail(String mail);
    Boolean existsByMail(String mail);
    @Query("SELECT u.userName FROM User u WHERE u.mail = :mail")
    String findUserNameByEmail(@Param("mail") String mail);
    @Query("SELECT u FROM User u WHERE u.mail = :mail")
    User findUserByEmail(@Param("mail") String mail);
}
