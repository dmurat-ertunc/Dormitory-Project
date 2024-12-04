package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface IStudentMgDao extends MongoRepository<StudentMg,String> {
    StudentMg findByMail(String mail);
    @Query("{ 'studentId': ?0 }")
    StudentMg getByStudentId(Long id);
}
