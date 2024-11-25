package com.dme.DormitoryProject.dtos.studentGetPermission;

import com.dme.DormitoryProject.entity.StudentGetPermission;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentGetPermissionMapper {

    @Autowired
    private static IStudentDao studentDao;

    public StudentGetPermissionMapper(IStudentDao studentDao){
        this.studentDao=studentDao;
    }

    public static StudentGetPermissionDTO toDto(StudentGetPermission studentGetPermission){

        StudentGetPermissionDTO studentGetPermissionDTO = new StudentGetPermissionDTO();

        studentGetPermissionDTO.setId(studentGetPermission.getId());
        studentGetPermissionDTO.setStartDate(studentGetPermission.getStartDate());
        studentGetPermissionDTO.setEndDate(studentGetPermission.getEndDate());
        studentGetPermissionDTO.setApproval(studentGetPermission.isApproval());
        studentGetPermissionDTO.setStudentId(studentGetPermission.getStudent().getId());
        studentGetPermissionDTO.setStudentBirthDate(studentGetPermission.getStudent().getBirthDate());
        studentGetPermissionDTO.setStudentMail(studentGetPermission.getStudent().getMail());
        studentGetPermissionDTO.setStudentName(studentGetPermission.getStudent().getName());
        studentGetPermissionDTO.setStudentSurName(studentGetPermission.getStudent().getSurName());
        studentGetPermissionDTO.setStudentTcNo(studentGetPermission.getStudent().getTcNo());
        studentGetPermissionDTO.setStudentVerify(studentGetPermission.getStudent().getVerification());

        return studentGetPermissionDTO;
    }

    public static StudentGetPermission toEntity(StudentGetPermissionDTO studentGetPermissionDTO){

        StudentGetPermission studentGetPermission = new StudentGetPermission();

        studentGetPermission.setApproval(studentGetPermissionDTO.isApproval());
        studentGetPermission.setEndDate(studentGetPermissionDTO.getEndDate());
        studentGetPermission.setStartDate(studentGetPermissionDTO.getStartDate());
        studentGetPermission.setStudent(studentDao.getById(studentGetPermissionDTO.getStudentId()));

        return studentGetPermission;
    }
}
