package com.dme.DormitoryProject.dtos.rentalDtos;

import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;

public class RentalMapper {

    @Autowired
    private static IStudentDao studentDao;
    @Autowired
    private static ISportAreaDao sportAreaDao;

    public RentalMapper(ISportAreaDao sportAreaDao, IStudentDao studentDao){
        this.sportAreaDao = sportAreaDao;
        this.studentDao=studentDao;
    }

    public static RentalDTO toDTO(Rental rental){
        RentalDTO rentalDTO = new RentalDTO();

        rentalDTO.setId(rental.getId());
        rentalDTO.setEndTime(rental.getEndTime());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setStartTime(rental.getStartTime());
        rentalDTO.setSporType(rental.getSportArea().getSporType());
        rentalDTO.setSportAreaId(rental.getSportArea().getId());
        rentalDTO.setStudentId(rental.getStudent().getId());
        rentalDTO.setStudentBirthDate(rental.getStudent().getBirthDate());
        rentalDTO.setStudentMail(rental.getStudent().getMail());
        rentalDTO.setStudentName(rental.getStudent().getName());
        rentalDTO.setStudentSurName(rental.getStudent().getSurName());
        rentalDTO.setStudenTcNo(rental.getStudent().getTcNo());
        rentalDTO.setStudentVerify(rental.getStudent().getVerification());

        return rentalDTO;
    }

    public static Rental toEntity(RentalDTO rentalDTO){
        Rental rental = new Rental();

        rental.setEndTime(rentalDTO.getEndTime());
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setStartTime(rentalDTO.getStartTime());
        rental.setStudent(studentDao.getById(rentalDTO.getStudentId()));
        rental.setSportArea(sportAreaDao.getById(rentalDTO.getSportAreaId()));

        return rental;
    }
}
