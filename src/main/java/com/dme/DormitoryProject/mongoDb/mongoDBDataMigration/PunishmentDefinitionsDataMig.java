//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.PunishmentDefinitions;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.PunishmentDefinitionsMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IPunishmentDefinitionsMgDao;
//import com.dme.DormitoryProject.repository.IPunishmentDao;
//import com.dme.DormitoryProject.repository.IPunishmentDeifinitionsDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.security.core.parameters.P;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PunishmentDefinitionsDataMig {
//
//    private IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao;
//    private IPunishmentDeifinitionsDao punishmentDeifinitionsDao;
//    private IPunishmentDao punishmentDao;
//
//    @Autowired
//    public PunishmentDefinitionsDataMig(IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao, IPunishmentDao punishmentDao,
//                                        IPunishmentDeifinitionsDao punishmentDeifinitionsDao) {
//        this.punishmentDefinitionsMgDao = punishmentDefinitionsMgDao;
//        this.punishmentDeifinitionsDao = punishmentDeifinitionsDao;
//        this.punishmentDao=punishmentDao;
//    }
//    @Scheduled(cron = "50 00 14 * * ?")
//    public void migration(){
//        List<PunishmentDefinitions> punishmentDefinitionsList = punishmentDeifinitionsDao.findAll();
//
//        for (PunishmentDefinitions punishmentDefinitions : punishmentDefinitionsList){
//            if(!punishmentDefinitions.isThrowMongo()){
//                PunishmentDefinitionsMg punishmentDefinitionsMg = new PunishmentDefinitionsMg();
//
//                punishmentDefinitionsMg.setPunishmentDefinitionsId(punishmentDefinitions.getId());
//                punishmentDefinitionsMg.setDescription(punishmentDefinitions.getDescription());
//                punishmentDefinitionsMg.setPenaltyScore(punishmentDefinitions.getPenaltyScore());
//                punishmentDefinitionsMg.setDeleted(punishmentDefinitions.isDeleted());
//                punishmentDefinitionsMg.setAddDate(punishmentDefinitions.getAddDate());
//                punishmentDefinitionsMg.setPunishments(punishmentDao.findByPunishmentDefinitionsId(punishmentDefinitions.getId()));
//
//                punishmentDefinitions.setThrowMongo(true);
//
//                punishmentDefinitionsMgDao.save(punishmentDefinitionsMg);
//                punishmentDeifinitionsDao.save(punishmentDefinitions);
//            }
//        }
//    }
//}
