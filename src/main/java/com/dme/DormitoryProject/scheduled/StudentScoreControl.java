package com.dme.DormitoryProject.scheduled;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentScoreControl {

    private IStudentDao studentDao;

    public StudentScoreControl(IStudentDao studentDao){
        this.studentDao=studentDao;
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
}
