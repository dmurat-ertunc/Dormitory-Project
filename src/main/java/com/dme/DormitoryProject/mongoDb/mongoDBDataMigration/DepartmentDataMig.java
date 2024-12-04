//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Department;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.DepartmentMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IDepartmentMgDao;
//import com.dme.DormitoryProject.repository.IDepartmentDao;
//import com.dme.DormitoryProject.repository.IPersonelRequestFormDao;
//import com.dme.DormitoryProject.repository.IStaffDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DepartmentDataMig {
//
//    private IDepartmentMgDao departmentMgDao;
//    private IDepartmentDao departmentDao;
//    private IStaffDao staffDao;
//    private IPersonelRequestFormDao personelRequestFormDao;
//
//    @Autowired
//    public DepartmentDataMig(IDepartmentMgDao departmentMgDao, IDepartmentDao departmentDao, IStaffDao staffDao,
//                             IPersonelRequestFormDao personelRequestFormDao) {
//        this.departmentMgDao = departmentMgDao;
//        this.departmentDao = departmentDao;
//        this.staffDao=staffDao;
//        this.personelRequestFormDao=personelRequestFormDao;
//    }
//
//    @Scheduled(cron = "00 35 11 * * ?")
//    public void migration(){
//
//        List<Department> departmentList = departmentDao.findAll();
//
//        for (Department department : departmentList){
//            if (!department.isThrowMongo()){
//                DepartmentMg departmentMg = new DepartmentMg();
//
//                departmentMg.setDepartmentId(department.getId());
//                departmentMg.setName(department.getName());
//                departmentMg.setAddDate(department.getAddDate());
//                departmentMg.setDeleted(department.isDeleted());
//                departmentMg.setStaffList(staffDao.findByDepartmentId(department.getId()));
//                departmentMg.setPersonnelRequestForms(personelRequestFormDao.findByDepartmentId(department.getId()));
//
//                department.setThrowMongo(true);
//
//                departmentMgDao.save(departmentMg);
//                departmentDao.save(department);
//            }
//        }
//    }
//}
