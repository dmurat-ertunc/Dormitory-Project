package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.dtos.studentGetPermission.StudentGetPermissionDTO;
import com.dme.DormitoryProject.entity.StudentGetPermission;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentGetPermissionMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentGetPermissionMgDao;
import com.dme.DormitoryProject.repository.IStudentGetPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGetPermissionDataMig {

    private IStudentGetPermissionDao studentGetPermissionDao;
    private IStudentGetPermissionMgDao studentGetPermissionMgDao;

    @Autowired

    public StudentGetPermissionDataMig(IStudentGetPermissionDao studentGetPermissionDao, IStudentGetPermissionMgDao studentGetPermissionMgDao) {
        this.studentGetPermissionDao = studentGetPermissionDao;
        this.studentGetPermissionMgDao = studentGetPermissionMgDao;
    }
    @Scheduled(cron = "00 14 13 * * ?")
    public void migration(){
        List<StudentGetPermission> studentGetPermissionList = studentGetPermissionDao.findAll();

        for (StudentGetPermission studentGetPermission : studentGetPermissionList){
            if (!studentGetPermission.isThrowMongo()){
                StudentGetPermissionMg studentGetPermissionMg = new StudentGetPermissionMg();

                studentGetPermissionMg.setStudent(studentGetPermission.getStudent());
                studentGetPermissionMg.setStudentGetPermissionId(studentGetPermission.getId());
                studentGetPermissionMg.setApproval(studentGetPermission.isApproval());
                studentGetPermissionMg.setEndDate(studentGetPermission.getEndDate());
                studentGetPermissionMg.setStartDate(studentGetPermission.getStartDate());
                studentGetPermissionMg.setAddDate(studentGetPermission.getAddDate());
                studentGetPermissionMg.setDeleted(studentGetPermission.isDeleted());

                studentGetPermission.setThrowMongo(true);

                studentGetPermissionMgDao.save(studentGetPermissionMg);
                studentGetPermissionDao.save(studentGetPermission);
            }
        }
    }
}
