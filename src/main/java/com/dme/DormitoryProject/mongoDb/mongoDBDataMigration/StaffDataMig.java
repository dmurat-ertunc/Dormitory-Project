//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Staff;
//import com.dme.DormitoryProject.entity.Student;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StaffMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStaffMgDao;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentMgDao;
//import com.dme.DormitoryProject.repository.IStaffDao;
//import com.dme.DormitoryProject.repository.IStudentDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StaffDataMig {
//    private IStaffMgDao staffMgDao;
//    private IStaffDao staffDao;
//
//    @Autowired
//    public StaffDataMig(IStaffDao staffDao, IStaffMgDao staffMgDao) {
//        this.staffDao=staffDao;
//        this.staffMgDao=staffMgDao;
//    }
//    @Scheduled(cron = "10 35 11 * * ?")
//    public void migration(){
//        List<Staff> staffList = staffDao.findAll();
//
//        for (Staff staff : staffList){
//            if (!staff.isThrowMongo()){
//                StaffMg staffMg = new StaffMg();
//
//                staffMg.setStaffId(staff.getId());
//                staffMg.setMail(staff.getMail());
//                staffMg.setName(staff.getName());
//                staffMg.setSalary(staff.getSalary());
//                staffMg.setManager(staff.getManager());
//                staffMg.setTitle(staff.getTitle());
//                staffMg.setDepartment(staff.getDepartment());
//                staffMg.setPhoneNumber(staff.getPhoneNumber());
//                staffMg.setSurName(staff.getSurName());
//                staffMg.setAddDate(staff.getAddDate());
//                staffMg.setDeleted(staff.isDeleted());
//
//                staff.setThrowMongo(true);
//
//                staffDao.save(staff);
//                staffMgDao.save(staffMg);
//            }
//
//
//        }
//    }
//}
