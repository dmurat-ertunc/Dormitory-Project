package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.mongoDb.mongoDBRepository.*;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.reflections.Reflections;
import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataMigration {
    private IBookMgDao bookMgDao;
    private IBookRentalMgDao bookRentalMgDao;
    private IDepartmentMgDao departmentMgDao;
    private IEntryExitMgDao entryExitMgDao;
    private ILgoMgDao lgoMgDao;
    private ILogLevelMgDao logLevelMgDao;
    private IMailMgDao mailMgDao;
    private IManagerMgDao managerMgDao;
    private IMeaTimeMgDao meaTimeMgDao;
    private IPersonnelRequestFormMgDao personnelRequestFormMgDao;
    private IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao;
    private IPunishmentMgDao punishmentMgDao;
    private IRentalMgDao rentalMgDao;
    private IRolesMgDao rolesMgDao;
    private IRoomMgDao roomMgDao;
    private ISportAreaMgDao sportAreaMgDao;
    private IStaffMgDao staffMgDao;
    private IStudentGetPermissionMgDao studentGetPermissionMgDao;
    private IStudentMgDao saveAll;
    private IStudentRequestRentalMgDao studentRequestRentalMgDao;
    private ITitleMgDao titleMgDao;
    private IUniversityMgDao universityMgDao;
    private IUserMgDao userMgDao;

    public DataMigration(IBookMgDao bookMgDao, IBookRentalMgDao bookRentalMgDao,
                         IDepartmentMgDao departmentMgDao, IEntryExitMgDao entryExitMgDao,
                         ILgoMgDao lgoMgDao, ILogLevelMgDao logLevelMgDao, IMailMgDao mailMgDao,
                         IManagerMgDao managerMgDao, IMeaTimeMgDao meaTimeMgDao,
                         IPersonnelRequestFormMgDao personnelRequestFormMgDao,
                         IPunishmentDefinitionsMgDao punishmentDefinitionsMgDao, IPunishmentMgDao punishmentMgDao,
                         IRentalMgDao rentalMgDao, IRolesMgDao rolesMgDao, IRoomMgDao roomMgDao,
                         ISportAreaMgDao sportAreaMgDao, IStaffMgDao staffMgDao,
                         IStudentGetPermissionMgDao studentGetPermissionMgDao, IStudentMgDao saveAll,
                         IStudentRequestRentalMgDao studentRequestRentalMgDao, ITitleMgDao titleMgDao,
                         IUniversityMgDao universityMgDao, IUserMgDao userMgDao) {
        this.bookMgDao = bookMgDao;
        this.bookRentalMgDao = bookRentalMgDao;
        this.departmentMgDao = departmentMgDao;
        this.entryExitMgDao = entryExitMgDao;
        this.lgoMgDao = lgoMgDao;
        this.logLevelMgDao = logLevelMgDao;
        this.mailMgDao = mailMgDao;
        this.managerMgDao = managerMgDao;
        this.meaTimeMgDao = meaTimeMgDao;
        this.personnelRequestFormMgDao = personnelRequestFormMgDao;
        this.punishmentDefinitionsMgDao = punishmentDefinitionsMgDao;
        this.punishmentMgDao = punishmentMgDao;
        this.rentalMgDao = rentalMgDao;
        this.rolesMgDao = rolesMgDao;
        this.roomMgDao = roomMgDao;
        this.sportAreaMgDao = sportAreaMgDao;
        this.staffMgDao = staffMgDao;
        this.studentGetPermissionMgDao = studentGetPermissionMgDao;
        this.saveAll = saveAll;
        this.studentRequestRentalMgDao = studentRequestRentalMgDao;
        this.titleMgDao = titleMgDao;
        this.universityMgDao = universityMgDao;
        this.userMgDao = userMgDao;
    }

    @Transactional
    public void migrateTable(String tableName) {
//        List<PostgresEntity> postgresData = postgresRepository.findAll();
//
//        List<MongoEntity> mongoData = postgresData.stream().map(postgresEntity -> {
//            MongoEntity mongoEntity = new MongoEntity();
//            mongoEntity.setDataField1(postgresEntity.getDataField1());
//            mongoEntity.setDataField2(postgresEntity.getDataField2());
//            return mongoEntity;
//        }).collect(Collectors.toList());
//
//        mongoRepository.saveAll(mongoData);
        getTableName();
        System.out.println("Veriler başarıyla MongoDB'ye aktarıldı.");
    }

    private String getTableName(){
        String url = "jdbc:postgresql://localhost:5432/postgres"; // Veritabanı URL'i
        String schema = "DormitoryProjectDb";
        String user = "postgres"; // PostgreSQL kullanıcı adı
        String password = "123456"; // PostgreSQL şifre

        try (Connection connection = DriverManager.getConnection(url,user,password)) {
            System.out.println("Bağlantı başarılı!");

            // DatabaseMetaData kullanımı
            DatabaseMetaData metaData = connection.getMetaData();

            // Tablo isimlerini çek
            try (ResultSet tables = metaData.getTables(null, schema, "%", new String[]{"TABLE"})) {
                System.out.println("Tablolar:");
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("- " + tableName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sdsasad(){
        Map<String, String> tableToEntityMap = new HashMap<>();

        try {
            // Belirli bir paketteki sınıfları tara
            Reflections reflections = new Reflections("com.example.entities");
            for (Class<?> clazz : reflections.getTypesAnnotatedWith(Table.class)) {
                Table tableAnnotation = clazz.getAnnotation(Table.class);
                if (tableAnnotation != null) {
                    tableToEntityMap.put(tableAnnotation.name(), clazz.getSimpleName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
