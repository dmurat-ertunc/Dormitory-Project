//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.*;
//import com.dme.DormitoryProject.repository.IStudentDao;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import jakarta.transaction.Transactional;
//import org.apache.poi.ss.formula.functions.T;
//import org.reflections.Reflections;
//import org.springframework.aot.hint.annotation.Reflective;
//import org.springframework.stereotype.Service;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DataMigration {
//    private IBookMgDao bookMgDao;
//    private IBookRentalMgDao bookRentalMgDao;
//    private IDepartmentMgDao departmentMgDao;
//    private IEntryExitMgDao entryExitMgDao;
//    private ILgoMgDao lgoMgDao;
//    private ILogLevelMgDao logLevelMgDao;
//    private IMailMgDao mailMgDao;
//    private IManagerMgDao managerMgDao;
//    private IMeaTimeMgDao meaTimeMgDao;
//    private IPersonnelRequestFormMgDao personnelRequestFormMgDao;
//    private IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao;
//    private IPunishmentMgDao punishmentMgDao;
//    private IRentalMgDao rentalMgDao;
//    private IRolesMgDao rolesMgDao;
//    private IRoomMgDao roomMgDao;
//    private ISportAreaMgDao sportAreaMgDao;
//    private IStaffMgDao staffMgDao;
//    private IStudentGetPermissionMgDao studentGetPermissionMgDao;
//    private IStudentMgDao studentMgDao;
//    private IStudentRequestRentalMgDao studentRequestRentalMgDao;
//    private ITitleMgDao titleMgDao;
//    private IUniversityMgDao universityMgDao;
//    private IUserMgDao userMgDao;
//    private IStudentDao studentDao;
//
//    public DataMigration(IBookMgDao bookMgDao, IBookRentalMgDao bookRentalMgDao,
//                         IDepartmentMgDao departmentMgDao, IEntryExitMgDao entryExitMgDao,
//                         ILgoMgDao lgoMgDao, ILogLevelMgDao logLevelMgDao, IMailMgDao mailMgDao,
//                         IManagerMgDao managerMgDao, IMeaTimeMgDao meaTimeMgDao,
//                         IPersonnelRequestFormMgDao personnelRequestFormMgDao,
//                         IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao, IPunishmentMgDao punishmentMgDao,
//                         IRentalMgDao rentalMgDao, IRolesMgDao rolesMgDao, IRoomMgDao roomMgDao,
//                         ISportAreaMgDao sportAreaMgDao, IStaffMgDao staffMgDao,
//                         IStudentGetPermissionMgDao studentGetPermissionMgDao, IStudentMgDao saveAll,
//                         IStudentRequestRentalMgDao studentRequestRentalMgDao, ITitleMgDao titleMgDao,
//                         IUniversityMgDao universityMgDao, IUserMgDao userMgDao, IStudentDao studentDao) {
//        this.bookMgDao = bookMgDao;
//        this.bookRentalMgDao = bookRentalMgDao;
//        this.departmentMgDao = departmentMgDao;
//        this.entryExitMgDao = entryExitMgDao;
//        this.lgoMgDao = lgoMgDao;
//        this.logLevelMgDao = logLevelMgDao;
//        this.mailMgDao = mailMgDao;
//        this.managerMgDao = managerMgDao;
//        this.meaTimeMgDao = meaTimeMgDao;
//        this.personnelRequestFormMgDao = personnelRequestFormMgDao;
//        this.punishmentDefinitionsMgDao = punishmentDefinitionsMgDao;
//        this.punishmentMgDao = punishmentMgDao;
//        this.rentalMgDao = rentalMgDao;
//        this.rolesMgDao = rolesMgDao;
//        this.roomMgDao = roomMgDao;
//        this.sportAreaMgDao = sportAreaMgDao;
//        this.staffMgDao = staffMgDao;
//        this.studentGetPermissionMgDao = studentGetPermissionMgDao;
//        this.studentMgDao = saveAll;
//        this.studentRequestRentalMgDao = studentRequestRentalMgDao;
//        this.titleMgDao = titleMgDao;
//        this.universityMgDao = universityMgDao;
//        this.userMgDao = userMgDao;
//        this.studentDao=studentDao;
//    }
//
//    @Transactional
//    public void migrateTable() {
//
//        getTableName();
//        Map<String,String> entityName = getEntityName();
//
//        for (Map.Entry<String, String> entry : entityName.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("Entity: " + key + ", Name: " + value);
//
//            Class<?> userEntityClass = getEntityClassByName(entry.getValue());
//
//            List<?> test =  studentDao.findAll();
//            //test.get(0).getClass().getName()
//
//            System.out.println(userEntityClass);
//        }
//
//        System.out.println("Veriler başarıyla MongoDB'ye aktarıldı.");
//    }
//
//    public Class<?> getEntityClassByName(String entityName){
//        try {
//            Class<?> clazz = Class.forName("com.dme.DormitoryProject.entity."+entityName);
//            return clazz;
//        } catch (ClassNotFoundException e) {
//            // Eğer sınıf bulunamazsa, bu blok çalışır
//            System.out.println("Sınıf bulunamadı: " + e.getMessage());
//            return null;
//        }
//    }
//
//    private String getTableName(){
//        String url = "jdbc:postgresql://localhost:5432/postgres"; // Veritabanı URL'i
//        String schema = "DormitoryProjectDb";
//        String user = "postgres"; // PostgreSQL kullanıcı adı
//        String password = "123456"; // PostgreSQL şifre
//
//        try (Connection connection = DriverManager.getConnection(url,user,password)) {
//            System.out.println("Bağlantı başarılı!");
//
//            // DatabaseMetaData kullanımı
//            DatabaseMetaData metaData = connection.getMetaData();
//
//            // Tablo isimlerini çek
//            try (ResultSet tables = metaData.getTables(null, schema, "%", new String[]{"TABLE"})) {
//                System.out.println("Tablolar:");
//                while (tables.next()) {
//                    String tableName = tables.getString("TABLE_NAME");
//                    System.out.println("- " + tableName);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private Map<String, String> getEntityName(){
//        Map<String, String> tableToEntityMap = new HashMap<>();
//
//        try {
//            // Belirli bir paketteki sınıfları tara
//            Reflections reflections = new Reflections("com.dme.DormitoryProject.entity");
//            for (Class<?> clazz : reflections.getTypesAnnotatedWith(Table.class)) {
//                Table tableAnnotation = clazz.getAnnotation(Table.class);
//                if (tableAnnotation != null) {
//                    tableToEntityMap.put(tableAnnotation.name(), clazz.getSimpleName());
//                }
//            }
//            System.out.println(tableToEntityMap);
//            return tableToEntityMap;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
