package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(collection = "studentGetPermission")
public class StudentGetPermissionMg extends BaseEntityMg{
    private Long studentGetPermissionId;
    private LocalDate startDate;
    private LocalDate endDate;
    @DBRef
    private Student student;
    private boolean approval;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public Long getStudentGetPermissionId() {
        return studentGetPermissionId;
    }

    public void setStudentGetPermissionId(Long studentGetPermissionId) {
        this.studentGetPermissionId = studentGetPermissionId;
    }
}
