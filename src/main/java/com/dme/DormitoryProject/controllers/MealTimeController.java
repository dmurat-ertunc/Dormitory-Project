package com.dme.DormitoryProject.controllers;


import com.dme.DormitoryProject.business.services.IMealTimeSerivce;
import com.dme.DormitoryProject.dtos.mealTime.MealTimeDto;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mealTime")
public class MealTimeController {

    private IMealTimeSerivce mealTimeSerivce;

    @Autowired
    public MealTimeController(IMealTimeSerivce mealTimeSerivce){
        this.mealTimeSerivce=mealTimeSerivce;
    }

    @PostMapping("entryDiningHall/{id}")
    public Result entryDiningHall(@PathVariable Long id){
        return this.mealTimeSerivce.entryDiningHall(id);
    }

}
