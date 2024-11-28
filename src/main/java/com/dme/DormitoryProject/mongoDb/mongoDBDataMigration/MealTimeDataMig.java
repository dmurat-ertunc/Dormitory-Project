package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.MealTime;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.MealTimeMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IMeaTimeMgDao;
import com.dme.DormitoryProject.repository.IMealTimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealTimeDataMig {

    private IMealTimeDao mealTimeDao;
    private IMeaTimeMgDao meaTimeMgDao;

    @Autowired
    public MealTimeDataMig(IMealTimeDao mealTimeDao, IMeaTimeMgDao meaTimeMgDao) {
        this.mealTimeDao = mealTimeDao;
        this.meaTimeMgDao = meaTimeMgDao;
    }
    @Scheduled(cron = "00 00 14 * * ?")
    public void migration(){
        List<MealTime> mealTimeList = mealTimeDao.findAll();

        for (MealTime mealTime : mealTimeList){
            if (!mealTime.isThrowMongo()){
                MealTimeMg mealTimeMg = new MealTimeMg();

                mealTimeMg.setMeals(mealTime.getMeals());
                mealTimeMg.setMealTimeId(mealTime.getId());
                mealTimeMg.setEatTime(mealTime.getEatTime());
                mealTimeMg.setStudent(mealTime.getStudent());
                mealTimeMg.setDeleted(mealTime.isDeleted());
                mealTimeMg.setAddDate(mealTime.getAddDate());

                mealTime.setThrowMongo(true);

                mealTimeDao.save(mealTime);
                meaTimeMgDao.save(mealTimeMg);
            }
        }
    }
}
