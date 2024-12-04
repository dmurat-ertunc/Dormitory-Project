package com.dme.DormitoryProject.mongoDb.mongoDBRepository;

import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface IBookMgDao extends MongoRepository<BookMg,String> {
    Optional<BookMg> findByBookId(Long id);
    @Query("{ 'bookId': ?0 }")
    BookMg getByBookId(Long id);
    @Query("{ 'isEmpty': ?0 }")
    List<BookMg> findByIsEmpty(boolean isEmpty);


}
