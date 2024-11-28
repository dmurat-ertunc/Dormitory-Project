package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentMgDao;
import com.dme.DormitoryProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentDataMig {

    private IStudentMgDao studentMgDao;
    private IStudentDao studentDao;
    private IUniversityDao universityDao;
    private IBookRentalDao bookRentalDao;
    private IEntryExitDao entryExitDao;
    private IMealTimeDao mealTimeDao;
    private IPunishmentDao punishmentDao;
    private IRentalDao rentalDao;
    private IStudentGetPermissionDao studentGetPermissionDao;
    private IStudentRequestRentalDao studentRequestRentalDao;

    @Autowired
    public StudentDataMig(IStudentMgDao studentMgDao, IStudentDao studentDao, IUniversityDao universityDao,
                          IBookRentalDao bookRentalDao, IEntryExitDao entryExitDao, IMealTimeDao mealTimeDao,
                          IPunishmentDao punishmentDao, IRentalDao rentalDao, IStudentGetPermissionDao studentGetPermissionDao,
                          IStudentRequestRentalDao studentRequestRentalDao) {
        this.studentMgDao = studentMgDao;
        this.studentDao = studentDao;
        this.universityDao=universityDao;
        this.bookRentalDao=bookRentalDao;
        this.entryExitDao=entryExitDao;
        this.mealTimeDao=mealTimeDao;
        this.punishmentDao=punishmentDao;
        this.rentalDao=rentalDao;
        this.studentGetPermissionDao=studentGetPermissionDao;
        this.studentRequestRentalDao=studentRequestRentalDao;
    }
    @Scheduled(cron = "00 58 11 * * ?")
    public void migration(){
        List<Student> studentList = studentDao.findAll();

        for (Student student : studentList){
            if (student.isThrowMongo()){
                StudentMg studentMg = new StudentMg();

                studentMg.setStudentId(student.getId());
                studentMg.setBirthDate(student.getBirthDate());
                studentMg.setMail(student.getMail());
                studentMg.setName(student.getName());
                studentMg.setSurName(student.getSurName());
                studentMg.setTcNo(student.getTcNo());
                studentMg.setScore(student.getScore());
                studentMg.setVerification(student.getVerification());
                studentMg.setRemainingPermitHours(student.getRemainingPermitHours());
                studentMg.setRoom(student.getRoom());
                studentMg.setUniversity(universityDao.findByStudents_Id(student.getId()));
                studentMg.setBookRentals(bookRentalDao.findByStudentId(student.getId()));
                studentMg.setEntryExits(entryExitDao.findByStudentId(student.getId()));
                studentMg.setMealTimes(mealTimeDao.findByStudentId(student.getId()));
                studentMg.setPunishments(punishmentDao.findByStudentId(student.getId()));
                studentMg.setRentalList(rentalDao.findByStudentId(student.getId()));
                studentMg.setStudentRequestRentalList(studentRequestRentalDao.findByStudentId(student.getId()));
                studentMg.setStudentGetPermissions(studentGetPermissionDao.findByStudentId(student.getId()));


                student.setThrowMongo(true);

                studentDao.save(student);
                studentMgDao.save(studentMg);

            }
        }
    }
}
