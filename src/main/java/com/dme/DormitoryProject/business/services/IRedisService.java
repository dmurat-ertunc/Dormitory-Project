package com.dme.DormitoryProject.business.services;


import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;

public interface IRedisService {
    void setData(Long id);
    long getData(Long id);
    void waitStudentData(StudentDTO studentDTO);
    StudentDTO getStudentData();
    void waitData(Object data);
}
