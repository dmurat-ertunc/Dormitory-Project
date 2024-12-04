package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDBManager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.dtos.Book.BookMapper;
import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IBookMgService;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookRentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookRentalMgDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


import java.util.*;

@Service
public class BookMgManager extends BaseClass implements IBookMgService {

    private IBookMgDao bookMgDao;
    private IBookRentalMgDao bookRentalMgDao;

    @Autowired
    public BookMgManager(IBookMgDao bookMgDao, IBookRentalMgDao bookRentalMgDao){
        this.bookMgDao=bookMgDao;
        this.bookRentalMgDao=bookRentalMgDao;
    }


    @Override
    public Result getAll() {
        try {
            List<BookDto> bookDtos = entityToDtoList(bookMgDao.findAll(), BookMapper::toDtoMg);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookDtos);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result getByIdAndRentals(Long bookId) {
        Map<String,Object> bookAndRentals = new HashMap<>();
        try{
            BookMg bookMg = bookMgDao.findByBookId(bookId).orElseThrow(() ->
                    new RuntimeException("BookMg not found for id: " + bookId)
            );
            bookAndRentals.put("bookId", bookMg.getBookId());
            bookAndRentals.put("name", bookMg.getName());
            bookAndRentals.put("type", bookMg.getType());
            bookAndRentals.put("empty", bookMg.isEmpty());
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
        List<BookRentalMg> bookRentalMgList = bookRentalMgDao.findByBookId(bookId);
        bookAndRentals.put("bookRentals",bookRentalMgList);
        return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookAndRentals);
    }

    @Override
    public Result save(BookDto bookDto) {
        BookMg bookMg = (dtoToEntity(bookDto,BookMapper::toEntityMg));
        bookMg.setEmpty(true);
        bookMg.setBookId(Math.abs(UUID.randomUUID().getMostSignificantBits())); // UUID nin en yüksek 64 bitlik alanını al
        bookMgDao.insert(bookMg);
        return new SuccesResult(JsonFileReader.getMessage("201","tr"),true);
    }
}
