//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Manager;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.ManagerMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IManagerMgDao;
//import com.dme.DormitoryProject.repository.IManagerDao;
//import com.dme.DormitoryProject.repository.IPersonelRequestFormDao;
//import com.dme.DormitoryProject.repository.IStaffDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ManagerDataMig {
//
//    private IManagerMgDao managerMgDao;
//    private IManagerDao managerDao;
//    private IStaffDao staffDao;
//    private IPersonelRequestFormDao personelRequestFormDao;
//
//
//    @Autowired
//    public ManagerDataMig(IManagerMgDao managerMgDao, IManagerDao managerDao, IStaffDao staffDao, IPersonelRequestFormDao personelRequestFormDao) {
//        this.managerMgDao = managerMgDao;
//        this.managerDao = managerDao;
//        this.personelRequestFormDao=personelRequestFormDao;
//        this.staffDao=staffDao;
//    }
//    @Scheduled(cron = "00 35 11 * * ?")
//    public void migration(){
//        List<Manager> managerList = managerDao.findAll();
//
//        for (Manager manager : managerList){
//            if (!manager.isThrowMongo()){
//                ManagerMg managerMg = new ManagerMg();
//
//                managerMg.setManagerId(manager.getId());
//                managerMg.setName(manager.getName());
//                managerMg.setMail(manager.getMail());
//                managerMg.setPhoneNumber(manager.getPhoneNumber());
//                managerMg.setSalary(manager.getSalary());
//                managerMg.setSurName(manager.getSurName());
//                managerMg.setTitle(manager.getTitle());
//                managerMg.setAddDate(manager.getAddDate());
//                managerMg.setDeleted(manager.isDeleted());
//                managerMg.setStaffList(staffDao.findByManagerId(manager.getId()));
//                managerMg.setPersonnelRequestForms(personelRequestFormDao.findByManagerId(manager.getId()));
//
//                manager.setThrowMongo(true);
//
//                managerMgDao.save(managerMg);
//                managerDao.save(manager);
//            }
//        }
//    }
//}
