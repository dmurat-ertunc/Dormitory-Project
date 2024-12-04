package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.mealTime.Meals;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "mealTime")
public class MealTimeMg extends BaseEntityMg{
    private Long mealTimeId;
    @BsonRepresentation(BsonType.STRING)
    private Meals meals;
    private LocalTime eatTime;
    @DBRef
    private StudentMg student;

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

    public StudentMg getStudent() {
        return student;
    }

    public void setStudent(StudentMg student) {
        this.student = student;
    }

    public Long getMealTimeId() {
        return mealTimeId;
    }

    public void setMealTimeId(Long mealTimeId) {
        this.mealTimeId = mealTimeId;
    }
}
