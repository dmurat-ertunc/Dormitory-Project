package com.dme.DormitoryProject.scheduled;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class StudentScoreControl {

    private IStudentDao studentDao;
    private IPunishmentDao punishmentDao;

    public StudentScoreControl(IStudentDao studentDao, IPunishmentDao punishmentDao){
        this.studentDao=studentDao;
        this.punishmentDao=punishmentDao;
    }

    @Scheduled(fixedRate = 3600000)
    public void StudentPointsisEquelsZero(){

        List<Student> studentList = studentDao.findAll();

        for (Student student : studentList){
            if (student.getScore() <= 0){
                student.setDeleted(true);
            }
        }
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void StudentsAddScore(){
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<Student> studentList = studentDao.findStudentsWithNoPunishmentsInLast30Days(thirtyDaysAgo);

        for (Student student : studentList){
            student.setScore(student.getScore() + 15);
            studentDao.save(student);
        }


    }
}
