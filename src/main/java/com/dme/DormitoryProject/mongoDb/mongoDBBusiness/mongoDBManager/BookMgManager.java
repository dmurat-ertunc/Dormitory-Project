package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDBManager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.dtos.Book.BookMapper;
import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IBookMgService;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMgManager extends BaseClass implements IBookMgService {

    private IBookMgDao bookMgDao;

    @Autowired
    public BookMgManager(IBookMgDao bookMgDao){
        this.bookMgDao=bookMgDao;
    }


    @Override
    public Result getAll() {
        try {
            List<BookDto> bookDtos = entityToDtoList(bookMgDao.findAll(), BookMapper::toDtoMg);
            List<BookMg> bookMgs = bookMgDao.findAll();
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookDtos);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result save(BookDto bookDto) {
        BookMg bookMg = bookMgDao.insert(dtoToEntity(bookDto,BookMapper::toEntityMg));
        return new SuccesResult(JsonFileReader.getMessage("201","tr"),true);
    }
}
