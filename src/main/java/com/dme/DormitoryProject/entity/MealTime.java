package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.mealTime.Meals;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "mealTimeTbl")
public class MealTime extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Meals meals;
    private LocalTime eatTime;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
