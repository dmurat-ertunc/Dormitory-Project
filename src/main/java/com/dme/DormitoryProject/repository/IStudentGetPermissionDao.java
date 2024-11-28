package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.StudentGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentGetPermissionDao extends JpaRepository<StudentGetPermission,Long> {
    List<StudentGetPermission> findByStudentId(Long id);
}
