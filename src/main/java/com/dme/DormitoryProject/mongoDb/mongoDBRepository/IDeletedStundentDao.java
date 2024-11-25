package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.mongoDb.mongoDBEntity.DeletedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface IDeletedStundentDao extends MongoRepository<DeletedStudent,String> {

}
