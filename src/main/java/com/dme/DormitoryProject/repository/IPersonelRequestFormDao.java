package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonelRequestFormDao extends JpaRepository<PersonnelRequestForm , Long> {
    List<PersonnelRequestForm> findByDepartmentId(Long id);
    List<PersonnelRequestForm> findByManagerId(Long id);
    List<PersonnelRequestForm> findByTitleId(Long id);
}
