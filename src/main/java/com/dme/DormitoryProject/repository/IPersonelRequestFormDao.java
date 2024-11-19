package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonelRequestFormDao extends JpaRepository<Long, PersonnelRequestForm> {
}
