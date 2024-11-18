package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.response.Result;

public interface IStudentRequestRentalService {
    void addRequest(StudentRequestRentalDTO studentRequestRentalDTO);
    Result permitRentalRequest(Long id);
    Result rejectedRentalRequest(Long id);
}
