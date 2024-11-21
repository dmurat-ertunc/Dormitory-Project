package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IBookService;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.dtos.Book.BookMapper;
import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.repository.IBookDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookManager extends BaseClass implements IBookService {

    private IBookDao bookDao;

    @Autowired
    public BookManager(IBookDao bookDao){
        this.bookDao=bookDao;
    }

    @Override
    public Result getAll() {
        try {
            List<BookDto> bookDtos = entityToDtoList(bookDao.findAll(), BookMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookDtos);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result getName(String name) {
        try {
            List<BookDto> bookDtos = entityToDtoList(bookDao.findByNameContaining(name),BookMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookDtos);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result save(BookDto bookDto) {
        bookDao.save(dtoToEntity(bookDto,BookMapper::toEntity));
        return new SuccessDataResult(JsonFileReader.getMessage("201","tr"),true,bookDto);
    }

    @Override
    public Result saveAll(List<BookDto> bookDtoList) {
        List<Book> books = new ArrayList<>();
        for (BookDto bookDto: bookDtoList){
            books.add(dtoToEntity(bookDto,BookMapper::toEntity));
        }
        bookDao.saveAll(books);
        return new SuccessDataResult(JsonFileReader.getMessage("201","tr"),true,bookDtoList);
    }

    @Override
    public Result update(Long id, BookDto bookDto) {
        Book book = bookDao.getById(id);
        book = dtoToEntity(bookDto,BookMapper::toEntity);
        bookDao.save(book);
        return new SuccessDataResult(JsonFileReader.getMessage("202","tr"),true,bookDto);
    }

    @Override
    public Result delete(Long id) {
        Book book = bookDao.getById(id);
        book.setDeleted(true);
        bookDao.save(book);
        return new SuccesResult(JsonFileReader.getMessage("203","tr"),true);
    }
}
