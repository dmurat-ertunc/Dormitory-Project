package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.StudentRequestRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRequestRentalDao extends JpaRepository<StudentRequestRental,Long> {
}
