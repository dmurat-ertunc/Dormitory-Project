package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "studentRequestRental")
public class StudentRequestRentalMg extends BaseEntityMg {
    private Long studentRequestRentalId;
    @DBRef
    private Student student;
    @BsonRepresentation(BsonType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    private String details;
    private LocalDate rentalDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @DBRef
    private SportArea sportArea;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public SportArea getSportArea() {
        return sportArea;
    }

    public void setSportArea(SportArea sportArea) {
        this.sportArea = sportArea;
    }

    public Long getStudentRequestRentalId() {
        return studentRequestRentalId;
    }

    public void setStudentRequestRentalId(Long studentRequestRentalId) {
        this.studentRequestRentalId = studentRequestRentalId;
    }
}
