package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.response.Result;

public interface IStaffService {
    Result getAll();
    Result getById(Long id);
    Result saveStaff(StaffDTO staffDTO,String password);
    Result deleteStaff(Long id);
    Result updateStaff(Long id, StaffDTO staffDTO);
    Result addPersonnelRequest(PersonelRequestFormDTO personelRequestFormDTO);
}
