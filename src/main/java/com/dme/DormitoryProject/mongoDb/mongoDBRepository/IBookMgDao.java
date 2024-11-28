package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface IBookMgDao extends MongoRepository<BookMg,String> {
}
