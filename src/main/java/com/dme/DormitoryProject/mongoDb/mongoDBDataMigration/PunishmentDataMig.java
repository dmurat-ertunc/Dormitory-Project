package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Punishments;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.PunishmentsMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IPunishmentMgDao;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunishmentDataMig {

    private IPunishmentDao punishmentDao;
    private IPunishmentMgDao punishmentMgDao;

    @Autowired
    public PunishmentDataMig(IPunishmentDao punishmentDao, IPunishmentMgDao punishmentMgDao) {
        this.punishmentDao = punishmentDao;
        this.punishmentMgDao = punishmentMgDao;
    }
    @Scheduled(cron = "40 00 14 * * ?")
    public void migration(){
        List<Punishments> punishmentsList = punishmentDao.findAll();

        for (Punishments punishments : punishmentsList){
            if (!punishments.isThrowMongo()){
                PunishmentsMg punishmentsMg = new PunishmentsMg();

                punishmentsMg.setPunishmentId(punishments.getId());
                punishmentsMg.setPunishmentTime(punishments.getPunishmentTime());
                punishmentsMg.setStudent(punishments.getStudent());
                punishmentsMg.setPunishmentDefinitions(punishments.getPunishmentDefinitions());
                punishmentsMg.setDeleted(punishments.isDeleted());
                punishmentsMg.setAddDate(punishments.getAddDate());

                punishmentsMg.setThrowMongo(true);

                punishmentMgDao.save(punishmentsMg);
                punishmentDao.save(punishments);
            }
        }
    }
}
