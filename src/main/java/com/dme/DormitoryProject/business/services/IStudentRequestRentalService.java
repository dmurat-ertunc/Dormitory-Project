package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.response.Result;

public interface IStudentRequestRentalService {
    void addRequest(StudentRequestRentalDTO studentRequestRentalDTO);
    Result permitRentalRequest(Long id);
    Result rejectedRentalRequest(Long id);
}
