package com.dme.DormitoryProject.dtos.mealTime;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.mealTime.Meals;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

public class MealTimeDto {
    private Meals meals;
    private LocalTime eatTime;
    private Long studentId;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;

    public MealTimeDto(){

    }

    public MealTimeDto(Meals meals, LocalTime eatTime, Long studentId, String studentMail,
                       String studentName, String studentSurName,
                       String studentTcNo, boolean studentVerify) {
        this.meals = meals;
        this.eatTime = eatTime;
        this.studentId = studentId;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
    }

    public Meals getMeals() {
        return meals;
    }
    public void setMeals(Meals meals) {
        this.meals = meals;
    }
    public LocalTime getEatTime() {
        return eatTime;
    }
    public void setEatTime(LocalTime eatTime) {
        this.eatTime = eatTime;
    }
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getStudentMail() {
        return studentMail;
    }
    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentSurName() {
        return studentSurName;
    }
    public void setStudentSurName(String studentSurName) {
        this.studentSurName = studentSurName;
    }
    public String getStudentTcNo() {
        return studentTcNo;
    }
    public void setStudentTcNo(String studentTcNo) {
        this.studentTcNo = studentTcNo;
    }
    public boolean isStudentVerify() {
        return studentVerify;
    }
    public void setStudentVerify(boolean studentVerify) {
        this.studentVerify = studentVerify;
    }
}
