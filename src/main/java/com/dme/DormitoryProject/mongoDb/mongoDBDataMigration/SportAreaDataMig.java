package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.SportAreaMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IRentalMgDao;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.ISportAreaMgDao;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportAreaDataMig {

    private ISportAreaMgDao sportAreaMgDao;
    private ISportAreaDao sportAreaDao;
    private IRentalDao rentalDao;
    private IStudentRequestRentalDao studentRequestRentalDao;

    @Autowired
    public SportAreaDataMig(ISportAreaMgDao sportAreaMgDao, ISportAreaDao sportAreaDao, IRentalDao rentalDao,
                            IStudentRequestRentalDao studentRequestRentalDao) {
        this.sportAreaMgDao = sportAreaMgDao;
        this.sportAreaDao = sportAreaDao;
        this.rentalDao = rentalDao;
        this.studentRequestRentalDao = studentRequestRentalDao;
    }

    @Scheduled(cron = "30 30 14 * * ?")
    public void migration(){
        List<SportArea> sportAreaList = sportAreaDao.findAll();

        for (SportArea sportArea : sportAreaList){
            if (!sportArea.isThrowMongo()){
                SportAreaMg sportAreaMg = new SportAreaMg();

                sportAreaMg.setSportAreaId(sportArea.getId());
                sportAreaMg.setSporType(sportArea.getSporType());
                sportAreaMg.setDeleted(sportArea.isDeleted());
                sportAreaMg.setAddDate(sportArea.getAddDate());
                sportAreaMg.setRentalList(rentalDao.findBySportAreaId(sportArea.getId()));
                sportAreaMg.setStudentRequestRentalList(studentRequestRentalDao.findBySportAreaId(sportArea.getId()));

                sportArea.setThrowMongo(true);

                sportAreaDao.save(sportArea);
                sportAreaMgDao.save(sportAreaMg);
            }
        }
    }

}
