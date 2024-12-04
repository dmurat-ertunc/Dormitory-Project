//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.University;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.UniversityMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IUniversityMgDao;
//import com.dme.DormitoryProject.repository.IStudentDao;
//import com.dme.DormitoryProject.repository.IUniversityDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UniversityDataMig {
//
//    private IStudentDao studentDao;
//    private IUniversityDao universityDao;
//    private IUniversityMgDao universityMgDao;
//
//    @Autowired
//    public UniversityDataMig(IStudentDao studentDao, IUniversityDao universityDao, IUniversityMgDao universityMgDao) {
//        this.studentDao = studentDao;
//        this.universityDao = universityDao;
//        this.universityMgDao = universityMgDao;
//    }
//
//    @Scheduled(cron = "30 35 11 * * ?")
//    public void migration(){
//        List<University> universityList = universityDao.findAll();
//
//        for (University university : universityList){
//            if (!university.isThrowMongo()){
//                UniversityMg universityMg = new UniversityMg();
//
//                universityMg.setUniversityId(university.getId());
//                universityMg.setcity(university.getcity());
//                universityMg.setmail(university.getmail());
//                universityMg.setName(university.getName());
//                universityMg.setphoneNumber(university.getphoneNumber());
//                universityMg.setAddDate(university.getAddDate());
//                universityMg.setDeleted(university.isDeleted());
//                universityMg.setStudents(studentDao.findByUniversity_Id(university.getId()));
//
//                university.setThrowMongo(true);
//
//                universityMgDao.save(universityMg);
//                universityDao.save(university);
//            }
//        }
//    }
//}
