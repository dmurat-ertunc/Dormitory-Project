package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.response.Result;

public interface IDepartmentService {
    Result getAll();
    Result getById(Long id);
    Result saveDepartment(DepartmentDTO departmentDTO);
    Result updateDepartment(Long id, DepartmentDTO departmentDTO);
    Result deleteDepartment(Long id);
    Result startingWithWord(String prefix);
}
