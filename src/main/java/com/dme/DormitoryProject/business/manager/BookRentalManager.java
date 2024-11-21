package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IBookRentalSerivice;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.dtos.Book.BookMapper;
import com.dme.DormitoryProject.dtos.bookRental.BookRentalDto;
import com.dme.DormitoryProject.dtos.bookRental.BookRentalMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.IBookDao;
import com.dme.DormitoryProject.repository.IBookRentalDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IUserDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookRentalManager extends BaseClass implements IBookRentalSerivice {

    private IBookRentalDao bookRentalDao;
    private IBookDao bookDao;
    private IUserDao userDao;
    private IStudentDao studentDao;

    @Autowired
    public BookRentalManager(IBookRentalDao bookRentalDao, IBookDao bookDao, IUserDao userDao, IStudentDao studentDao){
        this.bookRentalDao=bookRentalDao;
        this.bookDao=bookDao;
        this.userDao=userDao;
        this.studentDao=studentDao;
    }

    @Override
    public Result getAll() {
        try {
            List<BookRentalDto> bookRentalDtos = entityToDtoList(bookRentalDao.findAll(), BookRentalMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookRentalDtos);
        }catch (Exception e){
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result emptyBooks() {
        try {
            List<BookDto> bookDtoList = entityToDtoList(bookDao.emptyBook(), BookMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookDtoList);
        }catch (Exception e){
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result addRentalBook(BookRentalDto bookRentalDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) { // CustomUserDetails, UserDetails'in bir özelleştirilmiş hali olmalı
            String username = ((UserDetails) principal).getUsername(); // Username değerini al

            User user = userDao.findUserByUsername(username);
            Student student = studentDao.findByMail(user.getMail());

            if (!bookIsEmptyControl(bookRentalDto.getBookId())){
                return new ErrorResult(JsonFileReader.getMessage("205","tr"),false);
            }

            Book book = bookDao.getById(bookRentalDto.getBookId());
            book.setEmpty(false);
            bookDao.save(book);

            bookRentalDto.setDaysRented(String.valueOf(ChronoUnit.DAYS.between(LocalDate.now(),bookRentalDto.getEndDate())));
            bookRentalDto.setDelivered(false);
            bookRentalDto.setDeliveryDate(null);
            bookRentalDto.setStudentId(student.getId());

            bookRentalDao.save(dtoToEntity(bookRentalDto,BookRentalMapper::toEntity));
            return new SuccessDataResult(JsonFileReader.getMessage("204","tr"),true,bookRentalDto);
        }
        return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
    }

    @Override
    public Result bookDelivery(Long id) {
        return null;
    }

    private boolean bookIsEmptyControl(Long id){
        Book book = bookDao.getById(id);
        if (book.isEmpty()){
            return true;
        }
        return false;
    }
}
