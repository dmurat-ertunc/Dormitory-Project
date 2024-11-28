package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.EntryExit;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.EntryExitMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IEntryExitMgDao;
import com.dme.DormitoryProject.repository.IEntryExitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryExitDataMig {

    private IEntryExitDao entryExitDao;
    private IEntryExitMgDao entryExitMgDao;

    @Autowired
    public EntryExitDataMig(IEntryExitDao entryExitDao, IEntryExitMgDao entryExitMgDao) {
        this.entryExitDao = entryExitDao;
        this.entryExitMgDao = entryExitMgDao;
    }
    @Scheduled(cron = "10 00 14 * * ?")
    public void migration(){
        List<EntryExit> entryExitList = entryExitDao.findAll();

        for (EntryExit entryExit : entryExitList){
            if (!entryExit.isThrowMongo()){
                EntryExitMg entryExitMg = new EntryExitMg();

                entryExitMg.setEntryExitID(entryExit.getId());
                entryExitMg.setEntryExit(entryExit.getEntryExit());
                entryExitMg.setStudent(entryExit.getStudent());
                entryExitMg.setMomentaryTime(entryExit.getMomentaryTime());
                entryExitMg.setDeleted(entryExit.isDeleted());
                entryExitMg.setAddDate(entryExit.getAddDate());

                entryExit.setThrowMongo(true);

                entryExitDao.save(entryExit);
                entryExitMgDao.save(entryExitMg);
            }
        }
    }
}
