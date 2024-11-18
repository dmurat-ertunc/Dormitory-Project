package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.response.Result;

public interface IManagerService {
    Result getAll();
    Result getById(Long id);
    Result saveManager(ManagerDTO managerDTO, String password);
    Result updateManager(Long id,ManagerDTO managerDTO);
    Result deleteManager(Long id);
    Result findBySalaryGreaterThan(int salary);
}
