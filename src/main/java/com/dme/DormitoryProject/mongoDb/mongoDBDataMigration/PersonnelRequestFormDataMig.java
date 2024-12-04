//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.PersonnelRequestForm;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.PersonnelRequestFormMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IPersonnelRequestFormMgDao;
//import com.dme.DormitoryProject.repository.IPersonelRequestFormDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PersonnelRequestFormDataMig {
//
//    private IPersonnelRequestFormMgDao personnelRequestFormMgDao;
//    private IPersonelRequestFormDao personelRequestFormDao;
//
//    @Autowired
//    public PersonnelRequestFormDataMig(IPersonnelRequestFormMgDao personnelRequestFormMgDao, IPersonelRequestFormDao personelRequestFormDao) {
//        this.personnelRequestFormMgDao = personnelRequestFormMgDao;
//        this.personelRequestFormDao = personelRequestFormDao;
//    }
//
//    @Scheduled(cron = "50 35 11 * * ?")
//    public void migration(){
//        List<PersonnelRequestForm> personnelRequestFormList = personelRequestFormDao.findAll();
//
//        for (PersonnelRequestForm personnelRequestForm : personnelRequestFormList){
//            if (!personnelRequestForm.isThrowMongo()){
//                PersonnelRequestFormMg personnelRequestFormMg = new PersonnelRequestFormMg();
//
//                personnelRequestFormMg.setPersonnelRequestFormId(personnelRequestForm.getId());
//                personnelRequestFormMg.setDepartment(personnelRequestForm.getDepartment());
//                personnelRequestFormMg.setTitle(personnelRequestForm.getTitle());
//                personnelRequestFormMg.setManager(personnelRequestForm.getManager());
//                personnelRequestFormMg.setRequestorManager(personnelRequestForm.getRequestorManager());
//                personnelRequestFormMg.setDescription(personnelRequestForm.getDescription());
//                personnelRequestFormMg.setStatus(personnelRequestForm.getStatus());
//                personnelRequestFormMg.setAddDate(personnelRequestForm.getAddDate());
//                personnelRequestFormMg.setDeleted(personnelRequestForm.isDeleted());
//
//
//                personnelRequestForm.setThrowMongo(true);
//
//                personnelRequestFormMgDao.save(personnelRequestFormMg);
//                personelRequestFormDao.save(personnelRequestForm);
//            }
//        }
//    }
//}
