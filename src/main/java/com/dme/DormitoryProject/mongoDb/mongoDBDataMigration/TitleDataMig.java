package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.TitleMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.ITitleMgDao;
import com.dme.DormitoryProject.repository.IPersonelRequestFormDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.repository.ITitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleDataMig {

    private ITitleMgDao titleMgDao;
    private ITitleDao titleDao;
    private IStaffDao staffDao;
    private IPersonelRequestFormDao personelRequestFormDao;

    @Autowired
    public TitleDataMig(ITitleMgDao titleMgDao, ITitleDao titleDao, IStaffDao staffDao,
                        IPersonelRequestFormDao personelRequestFormDao) {
        this.titleMgDao = titleMgDao;
        this.titleDao = titleDao;
        this.staffDao=staffDao;
        this.personelRequestFormDao=personelRequestFormDao;
    }

    @Scheduled(cron = "20 35 11 * * ?")
    public void migration(){
        List<Title> titleList = titleDao.findAll();

        for (Title title : titleList){
            if (!title.isThrowMongo()){
                TitleMg titleMg = new TitleMg();

                titleMg.setTitleId(title.getId());
                titleMg.setName(title.getName());
                titleMg.setDeleted(title.isDeleted());
                titleMg.setAddDate(title.getAddDate());
                titleMg.setStaffList(staffDao.findByTitleId(title.getId()));
                titleMg.setPersonnelRequestForms(personelRequestFormDao.findByTitleId(title.getId()));


                title.setThrowMongo(true);

                titleMgDao.save(titleMg);
                titleDao.save(title);
            }
        }
    }
}
