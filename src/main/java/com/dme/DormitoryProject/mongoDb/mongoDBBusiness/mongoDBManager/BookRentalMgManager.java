package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDBManager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.dtos.Book.BookMapper;
import com.dme.DormitoryProject.dtos.bookRental.BookRentalDto;
import com.dme.DormitoryProject.dtos.bookRental.BookRentalMapper;
import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.User;
import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IBookRentalMgService;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookRentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.StudentMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookRentalMgDao;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentMgDao;
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
import java.util.Optional;
import java.util.UUID;
@Service
public class BookRentalMgManager extends BaseClass implements IBookRentalMgService {

    private IUserDao userDao;
    private IBookMgDao bookMgDao;
    private IStudentMgDao studentMgDao;
    private IBookRentalMgDao bookRentalMgDao;


    @Autowired
    public BookRentalMgManager(IUserDao userDao, IBookMgDao bookMgDao, IStudentMgDao studentMgDao,
                               IBookRentalMgDao bookRentalMgDao) {
        this.userDao = userDao;
        this.bookMgDao = bookMgDao;
        this.studentMgDao = studentMgDao;
        this.bookRentalMgDao = bookRentalMgDao;
    }

    @Override
    public Result getAll() {
        try {
            List<BookRentalDto> bookRentalDtos = entityToDtoList(bookRentalMgDao.findAll(), BookRentalMapper::toDtoMg);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,bookRentalDtos);
        }catch (Exception e){
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result emptyBooks() {
        try {
            List<BookDto> bookDtoList = entityToDtoList(bookMgDao.findByIsEmpty(true), BookMapper::toDtoMg);
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
            StudentMg studentMg = studentMgDao.findByMail(user.getMail());

            if (!bookIsEmptyControl(bookRentalDto.getBookId())){
                return new ErrorResult(JsonFileReader.getMessage("205","tr"),false);
            }

            BookMg bookMg = bookMgDao.findByBookId(bookRentalDto.getBookId()).orElseThrow(() ->
                    new RuntimeException("BookMg not found for id: " + bookRentalDto.getBookId())
            );
            bookMg.setEmpty(false);
            bookMgDao.insert(bookMg);

            BookRentalMg bookRentalMg = dtoToEntity(bookRentalDto, BookRentalMapper::toEntityMg);
            bookRentalMg.setDaysRented(String.valueOf(ChronoUnit.DAYS.between(LocalDate.now(),bookRentalDto.getEndDate())));
            bookRentalMg.setDelivered(false);
            bookRentalMg.setBookRentalId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
            bookRentalMg.setDeliveryDate(null);
            bookRentalMg.setStudent(studentMg);

            bookRentalMgDao.insert(bookRentalMg);
            return new SuccessDataResult(JsonFileReader.getMessage("204","tr"),true,bookRentalDto);
        }
        return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
    }

    @Override
    public Result bookDelivery(Long id) {
        return null;
    }

    private boolean bookIsEmptyControl(Long id){
        BookMg bookMg = bookMgDao.getByBookId(id);
        if (bookMg.isEmpty()){
            return true;
        }
        return false;
    }
}
