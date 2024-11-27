package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.entity.EntryExit;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.EntryExitMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface IEntryExitMgDao extends MongoRepository<EntryExitMg,String> {
}
