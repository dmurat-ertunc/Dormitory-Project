//package com.dme.DormitoryProject.mongoDb.mongoDBController;
//
//import com.dme.DormitoryProject.mongoDb.mongoDBDataMigration.DataMigration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/dataMigration")
//public class DataMigrationController {
//
//    private DataMigration dataMigration;
//
//    @Autowired
//    public DataMigrationController(DataMigration dataMigration){
//        this.dataMigration=dataMigration;
//    }
//
//    @PutMapping("migrationTable")
//    public ResponseEntity<String> migrationTable(){
//        dataMigration.migrateTable();
//        return ResponseEntity.ok("Akttarım başarılı");
//    }
//}
