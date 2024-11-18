package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.response.Result;

import java.util.List;


public interface IStudentService {
    Result findStudentById(Long id);
    Result getAll();
    List<Student> findStudentsOlderThan18Native();
    Result saveStudent(StudentDTO studentDTO, String password);
    List<Student> saveStudentAll(List<Student> students);
    Result updateStudent(Long id,StudentDTO studentDTO);
    Result deleteStudent(Long id);
    Result mailVerification(Long id, String mailCode);
    Result sendMail(Long id);
    Result findUniversityId(Long id);
}
