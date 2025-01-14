package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "rental")
public class RentalMg extends BaseEntityMg {
    private Long rentalId;
    @DBRef
    private SportAreaMg sportArea;
    private LocalDate rentalDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @DBRef
    private StudentMg student;

    public SportAreaMg getSportArea() {
        return sportArea;
    }
    public void setSportArea(SportAreaMg sportArea) {
        this.sportArea = sportArea;
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
    public StudentMg getStudent() {
        return student;
    }
    public void setStudent(StudentMg student) {
        this.student = student;
    }
    public Long getRentalId() {
        return rentalId;
    }
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}
