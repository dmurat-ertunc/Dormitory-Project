//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.LogLevel;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.LogLevelMg;
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
//public class LogLevelDataMig {
//
//    private ILogLevelDao logLevelDao;
//    private ILogLevelMgDao logLevelMgDao;
//    private ILgoDao lgoDao;
//
//    @Autowired
//    public LogLevelDataMig(ILogLevelDao logLevelDao, ILogLevelMgDao logLevelMgDao, ILgoDao lgoDao) {
//        this.logLevelDao = logLevelDao;
//        this.logLevelMgDao = logLevelMgDao;
//        this.lgoDao=lgoDao;
//    }
//
//    @Scheduled(cron = "00 35 11 * * ?")
//    public void migration(){
//        List<LogLevel> logLevelList = logLevelDao.findAll();
//
//        for (LogLevel logLevel : logLevelList){
//            if (!logLevel.isThrowMongo()){
//                LogLevelMg logLevelMg = new LogLevelMg();
//
//                logLevelMg.setLogLevelId(logLevel.getId());
//                logLevelMg.setDescription(logLevel.getDescription());
//                logLevelMg.setAddDate(logLevel.getAddDate());
//                logLevelMg.setDeleted(logLevel.isDeleted());
//                logLevelMg.setLgoList(lgoDao.findByLogLevelId(logLevel.getId()));
//
//                logLevel.setThrowMongo(true);
//                logLevelDao.save(logLevel);
//                logLevelMgDao.save(logLevelMg);
//            }
//        }
//    }
//}
