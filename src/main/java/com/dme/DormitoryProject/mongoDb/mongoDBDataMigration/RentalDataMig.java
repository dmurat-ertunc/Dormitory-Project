package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.RentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IRentalMgDao;
import com.dme.DormitoryProject.repository.IRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalDataMig {

    private IRentalDao rentalDao;
    private IRentalMgDao rentalMgDao;

    @Autowired
    public RentalDataMig(IRentalDao rentalDao, IRentalMgDao rentalMgDao) {
        this.rentalDao = rentalDao;
        this.rentalMgDao = rentalMgDao;
    }
    @Scheduled(cron = "20 30 14 * * ?")
    public void migration(){

        List<Rental> rentalList = rentalDao.findAll();

        for (Rental rental : rentalList){
            if (!rental.isThrowMongo()){

                RentalMg rentalMg = new RentalMg();

                rentalMg.setRentalId(rental.getId());
                rentalMg.setStudent(rental.getStudent());
                rentalMg.setSportArea(rental.getSportArea());
                rentalMg.setRentalDate(rental.getRentalDate());
                rentalMg.setEndTime(rental.getEndTime());
                rentalMg.setStartTime(rental.getStartTime());
                rentalMg.setAddDate(rental.getAddDate());
                rentalMg.setDeleted(rental.isDeleted());

                rental.setThrowMongo(true);

                rentalDao.save(rental);
                rentalMgDao.save(rentalMg);
            }
        }
    }
}
