package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDBManager;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IDeletedStudentService;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.DeletedStudent;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IDeletedStundentDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeletedStudentManager implements IDeletedStudentService {

    private IStudentDao studentDao;
    private IDeletedStundentDao deletedStundentDao;

    @Autowired
    public DeletedStudentManager(IStudentDao studentDao, IDeletedStundentDao deletedStundentDao){
        this.studentDao=studentDao;
        this.deletedStundentDao=deletedStundentDao;
    }

    @Scheduled(cron = "50 03 13 * * ?")
    @Override
    public void addDeletedStudent() {
        List<Student> deletedStudentList = studentDao.deletedStudent();

        for (Student student : deletedStudentList){
            DeletedStudent deletedStudent = new DeletedStudent();
            deletedStudent.setStudentId(student.getId());
            deletedStudent.setBirthDate(student.getBirthDate());
            deletedStudent.setEmail(student.getMail());
            deletedStudent.setName(student.getName());
            deletedStudent.setSurName(student.getSurName());
            deletedStudent.setTcNo(student.getTcNo());

            deletedStundentDao.save(deletedStudent);

        }
    }
}
