package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentMgDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IUniversityDao;
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

    @Autowired
    public StudentDataMig(IStudentMgDao studentMgDao, IStudentDao studentDao, IUniversityDao universityDao) {
        this.studentMgDao = studentMgDao;
        this.studentDao = studentDao;
        this.universityDao=universityDao;
    }
    @Scheduled(cron = "30 35 10 * * ?")
    public void migration(){
        List<Student> studentList = studentDao.findAll();

        for (Student student : studentList){
            if (!student.isThrowMongo()){
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

                student.setThrowMongo(true);

                studentDao.save(student);
                studentMgDao.save(studentMg);

            }
        }
    }
}
