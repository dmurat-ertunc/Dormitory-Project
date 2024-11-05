package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.RequestStatus;
import com.dme.DormitoryProject.enums.StudentRequestLocation;
import com.dme.DormitoryProject.enums.StudentRequestType;
import jakarta.persistence.*;

@Entity
@Table(name = "studentRequestTbl")
public class StudentRequest extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @Enumerated(EnumType.STRING)
    private StudentRequestType requestType;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    private String details;
    @Enumerated(EnumType.STRING)
    private StudentRequestLocation location;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(StudentRequestType requestType) {
        this.requestType = requestType;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public StudentRequestLocation getLocation() {
        return location;
    }

    public void setLocation(StudentRequestLocation location) {
        this.location = location;
    }



}
