package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.response.Result;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IRentalService {
    Result getAll();
    Result getById(Long id);
    Result deleteRental(Long id);
    Result updateRental(Long id, RentalDTO rentalDTO);
    Result afterRental(LocalTime startTime);
    Result emptyField(LocalTime startTime, LocalTime endTime, LocalDate date);
    Result addRentalRequest(StudentRequestRentalDTO studentRequestRentalDTO);
}
