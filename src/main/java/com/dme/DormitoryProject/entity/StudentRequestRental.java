package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.RequestStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "studentRequestTbl")
public class StudentRequestRental extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    private String details;
    private LocalDate rentalDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "sportAreaId", nullable = false)
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
}
