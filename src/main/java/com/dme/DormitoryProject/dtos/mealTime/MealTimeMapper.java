package com.dme.DormitoryProject.dtos.mealTime;

import com.dme.DormitoryProject.entity.MealTime;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MealTimeMapper {

    @Autowired
    private static IStudentDao studentDao;

    public MealTimeMapper(IStudentDao studentDao){
        this.studentDao=studentDao;
    }

    public static MealTimeDto toDto(MealTime mealTime){

        MealTimeDto mealTimeDto = new MealTimeDto();

        mealTimeDto.setMeals(mealTime.getMeals());
        mealTimeDto.setEatTime(mealTime.getEatTime());
        mealTimeDto.setStudentId(mealTime.getStudent().getId());
        mealTimeDto.setStudentMail(mealTime.getStudent().getMail());
        mealTimeDto.setStudentName(mealTime.getStudent().getName());
        mealTimeDto.setStudentSurName(mealTime.getStudent().getSurName());
        mealTimeDto.setStudentTcNo(mealTime.getStudent().getTcNo());
        mealTimeDto.setStudentVerify(mealTime.getStudent().getVerification());

        return mealTimeDto;
    }

    public static MealTime toEntity(MealTimeDto mealTimeDto){

        MealTime mealTime = new MealTime();

        mealTime.setEatTime(mealTimeDto.getEatTime());
        mealTime.setMeals(mealTimeDto.getMeals());
        mealTime.setStudent(studentDao.getById(mealTimeDto.getStudentId()));

        return mealTime;
    }
}
