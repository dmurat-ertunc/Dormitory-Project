package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookRentalMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface IBookRentalMgDao extends MongoRepository<BookRentalMg,String> {
    List<BookRentalMg> findByBookId(Long bookId);

}
