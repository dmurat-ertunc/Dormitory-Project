package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IMealTimeSerivce;
import com.dme.DormitoryProject.dtos.mealTime.MealTimeDto;
import com.dme.DormitoryProject.entity.MealTime;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.mealTime.Meals;
import com.dme.DormitoryProject.repository.IMealTimeDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class MealTimeManager implements IMealTimeSerivce {

    private IMealTimeDao mealTimeDao;
    private IStudentDao studentDao;

    @Autowired
    public MealTimeManager(IMealTimeDao mealTimeDao, IStudentDao studentDao){
        this.mealTimeDao=mealTimeDao;
        this.studentDao=studentDao;
    }

    @Override
    public Result entryDiningHall(Long id) {
        Student student = studentDao.getById(id);
        LocalTime momentaryTime = LocalTime.now();
        int momentaryHour = momentaryTime.getHour();
        switch (momentaryHour){
            case 7,8 -> {
                entryDiningHall(id,Meals.Kahvaltı);
                return new SuccesResult(JsonFileReader.getMessage("603","tr"),true);
            }
            case 12,13 -> {
                entryDiningHall(id,Meals.Öğlen_Yemeği);
                return new SuccesResult(JsonFileReader.getMessage("603","tr"),true);
            }
            case 18,20 -> {
                entryDiningHall(id,Meals.Akşam_Yemeği);
                return new SuccesResult(JsonFileReader.getMessage("603","tr"),true);
            }
            default -> {
                return new ErrorResult(JsonFileReader.getMessage("601","tr"),false);
            }
        }
    }

    private Object entryDiningHall(Long id , Meals meals){
        MealTime mealTime = new MealTime();
        LocalTime momentaryTime = LocalTime.now();
        Student student = studentDao.getById(id);
        MealTime lastMealTime = mealTimeDao.findLatestMealTimeByStudentId(id); // student e ait son veriyi getir

        if (mealTimeDao.existsByStudentId(id)){
            if (lastMealTime.getMeals().equals(meals) && Objects.equals(lastMealTime.getAddDate(), LocalDate.now())){
                return new ErrorResult(JsonFileReader.getMessage("602","tr"),false);
            }
        }

        mealTime.setEatTime(momentaryTime);
        mealTime.setMeals(meals);
        mealTime.setStudent(student);
        mealTimeDao.save(mealTime);
        return true;
    }

}
