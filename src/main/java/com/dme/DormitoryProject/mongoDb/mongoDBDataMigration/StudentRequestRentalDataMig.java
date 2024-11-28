package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.StudentRequestRental;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentRequestRentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentRequestRentalMgDao;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRequestRentalDataMig {

    private IStudentRequestRentalDao studentRequestRentalDao;
    private IStudentRequestRentalMgDao studentRequestRentalMgDao;

    @Autowired

    public StudentRequestRentalDataMig(IStudentRequestRentalDao studentRequestRentalDao,
                                       IStudentRequestRentalMgDao studentRequestRentalMgDao) {
        this.studentRequestRentalDao = studentRequestRentalDao;
        this.studentRequestRentalMgDao = studentRequestRentalMgDao;
    }

    @Scheduled(cron = "10 30 14 * * ?")
    public void migration(){
        List<StudentRequestRental>   studentRequestRentalList = studentRequestRentalDao.findAll();

        for (StudentRequestRental studentRequestRental : studentRequestRentalList){
            if (!studentRequestRental.isThrowMongo()){
                StudentRequestRentalMg studentRequestRentalMg = new StudentRequestRentalMg();

                studentRequestRentalMg.setStudent(studentRequestRental.getStudent());
                studentRequestRentalMg.setStudentRequestRentalId(studentRequestRental.getId());
                studentRequestRentalMg.setRentalDate(studentRequestRental.getRentalDate());
                studentRequestRentalMg.setDetails(studentRequestRental.getDetails());
                studentRequestRentalMg.setStatus(studentRequestRental.getStatus());
                studentRequestRentalMg.setSportArea(studentRequestRental.getSportArea());
                studentRequestRentalMg.setStartTime(studentRequestRental.getStartTime());
                studentRequestRentalMg.setEndTime(studentRequestRental.getEndTime());
                studentRequestRentalMg.setAddDate(studentRequestRental.getAddDate());
                studentRequestRentalMg.setDeleted(studentRequestRental.isDeleted());

                studentRequestRental.setThrowMongo(true);

                studentRequestRentalMgDao.save(studentRequestRentalMg);
                studentRequestRentalDao.save(studentRequestRental);
            }


        }
    }
}
