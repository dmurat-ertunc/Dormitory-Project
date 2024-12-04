//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Lgo;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.LgoMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.ILgoMgDao;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.ILogLevelMgDao;
//import com.dme.DormitoryProject.repository.ILgoDao;
//import com.dme.DormitoryProject.repository.ILogLevelDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class LgoDataMig {
//    private ILogLevelMgDao logLevelMgDao;
//    private ILgoMgDao lgoMgDao;
//    private ILgoDao lgoDao;
//    private ILogLevelDao logLevelDao;
//
//    @Autowired
//    public LgoDataMig(ILogLevelMgDao logLevelMgDao, ILgoMgDao lgoMgDao, ILgoDao lgoDao, ILogLevelDao logLevelDao) {
//        this.logLevelMgDao = logLevelMgDao;
//        this.lgoMgDao = lgoMgDao;
//        this.lgoDao = lgoDao;
//        this.logLevelDao = logLevelDao;
//    }
//
//    @Scheduled(cron = "00 35 11 * * ?")
//    public void migration(){
//        List<Lgo> lgoList = lgoDao.findAll();
//
//        for (Lgo lgo : lgoList){
//            if (!lgo.isThrowMongo()){
//                LgoMg lgoMg = new LgoMg();
//
//                lgoMg.setLgoId(lgo.getId());
//                lgoMg.setMessage(lgo.getMessage());
//                lgoMg.setAddDate(lgo.getAddDate());
//                lgoMg.setDeleted(lgo.isDeleted());
//                lgoMg.setLogLevel(lgo.getLogLevel());
//
//
//                lgo.setThrowMongo(true);
//
//                lgoDao.save(lgo);
//                lgoMgDao.save(lgoMg);
//            }
//        }
//    }
//}
