package com.dme.DormitoryProject.dtos.studentRentalDtos;

import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentRequestMapper {

    private static IStudentDao studentDao;
    private static ISportAreaDao sportAreaDao;

    public StudentRequestMapper(ISportAreaDao sportAreaDao, IStudentDao studentDao){
        this.sportAreaDao=sportAreaDao;
        this.studentDao=studentDao;
    }

    public static StudentRequestRentalDTO toDTO(StudentRequestRental studentRequestRental){
        StudentRequestRentalDTO studentRequestRentalDTO = new StudentRequestRentalDTO();

        studentRequestRentalDTO.setId(studentRequestRental.getId());
        studentRequestRentalDTO.setEndTime(studentRequestRental.getEndTime());
        studentRequestRentalDTO.setRentalDate(studentRequestRental.getRentalDate());
        studentRequestRentalDTO.setStartTime(studentRequestRental.getStartTime());
        studentRequestRentalDTO.setSporType(studentRequestRental.getSportArea().getSporType());
        studentRequestRentalDTO.setSportAreaId(studentRequestRental.getSportArea().getId());
        studentRequestRentalDTO.setStudentId(studentRequestRental.getStudent().getId());
        studentRequestRentalDTO.setStudentBirthDate(studentRequestRental.getStudent().getBirthDate());
        studentRequestRentalDTO.setStudentMail(studentRequestRental.getStudent().getMail());
        studentRequestRentalDTO.setStudentName(studentRequestRental.getStudent().getName());
        studentRequestRentalDTO.setStudentSurName(studentRequestRental.getStudent().getSurName());
        studentRequestRentalDTO.setStudenTcNo(studentRequestRental.getStudent().getTcNo());
        studentRequestRentalDTO.setStudentVerify(studentRequestRental.getStudent().getVerification());
        studentRequestRentalDTO.setDetails(studentRequestRental.getDetails());

        return studentRequestRentalDTO;
    }

    public static StudentRequestRental toEntity(StudentRequestRentalDTO studentRequestRentalDTO){
        StudentRequestRental studentRequestRental = new StudentRequestRental();

        studentRequestRental.setEndTime(studentRequestRentalDTO.getEndTime());
        studentRequestRental.setRentalDate(studentRequestRentalDTO.getRentalDate());
        studentRequestRental.setStartTime(studentRequestRentalDTO.getStartTime());
        studentRequestRental.setDetails(studentRequestRentalDTO.getDetails());
        studentRequestRental.setStudent(studentDao.getById(studentRequestRentalDTO.getStudentId()));
        studentRequestRental.setSportArea(sportAreaDao.getById(studentRequestRentalDTO.getSportAreaId()));

        return studentRequestRental;
    }
}
