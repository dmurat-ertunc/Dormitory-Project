package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleDao extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);

}
