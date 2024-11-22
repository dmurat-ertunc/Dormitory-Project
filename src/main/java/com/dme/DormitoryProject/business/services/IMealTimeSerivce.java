package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.mealTime.MealTimeDto;
import com.dme.DormitoryProject.response.Result;

public interface IMealTimeSerivce {
    Result entryDiningHall(Long id);
}
