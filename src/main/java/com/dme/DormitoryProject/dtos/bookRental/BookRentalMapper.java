package com.dme.DormitoryProject.dtos.bookRental;

import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.entity.BookRental;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookRentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IStudentMgDao;
import com.dme.DormitoryProject.repository.IBookDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookRentalMapper {

    @Autowired
    private static IStudentDao studentDao;
    @Autowired
    private static IBookDao bookDao;
    @Autowired
    private static IBookMgDao bookMgDao;
    @Autowired
    private static IStudentMgDao studentMgDao;

    public BookRentalMapper(IStudentDao studentDao, IBookDao bookDao, IStudentMgDao studentMgDao, IBookMgDao bookMgDao){
        this.bookDao=bookDao;
        this.studentDao=studentDao;
        this.studentMgDao=studentMgDao;
        this.bookMgDao=bookMgDao;
    }

    public static BookRentalDto toDto(BookRental bookRental){

        BookRentalDto bookRentalDto = new BookRentalDto();

        bookRentalDto.setDeliveryDate(bookRental.getDeliveryDate());
        bookRentalDto.setEndDate(bookRental.getEndDate());
        bookRentalDto.setDaysRented(bookRental.getDaysRented());
        bookRentalDto.setBookId(bookRental.getBook().getId());
        bookRentalDto.setBookName(bookRental.getBook().getName());
        bookRentalDto.setBookType(bookRental.getBook().getType());
        bookRentalDto.setStudentId(bookRental.getStudent().getId());
        bookRentalDto.setStudentBirthDate(bookRental.getStudent().getBirthDate());
        bookRentalDto.setStudentMail(bookRental.getStudent().getMail());
        bookRentalDto.setStudentName(bookRental.getStudent().getName());
        bookRentalDto.setStudentSurName(bookRental.getStudent().getSurName());
        bookRentalDto.setStudentVerify(bookRental.getStudent().getVerification());
        bookRentalDto.setStudentTcNo(bookRental.getStudent().getTcNo());
        bookRentalDto.setDelivered(bookRental.isDelivered());

        return bookRentalDto;
    }

    public static BookRentalDto toDtoMg(BookRentalMg bookRentalMg){

        BookRentalDto bookRentalDto = new BookRentalDto();

        bookRentalDto.setDeliveryDate(bookRentalMg.getDeliveryDate());
        bookRentalDto.setEndDate(bookRentalMg.getEndDate());
        bookRentalDto.setDaysRented(bookRentalMg.getDaysRented());
        bookRentalDto.setBookId(bookRentalMg.getBook().getBookId());
        bookRentalDto.setBookName(bookRentalMg.getBook().getName());
        bookRentalDto.setBookType(bookRentalMg.getBook().getType());
        bookRentalDto.setStudentId(bookRentalMg.getStudent().getStudentId());
        bookRentalDto.setStudentBirthDate(bookRentalMg.getStudent().getBirthDate());
        bookRentalDto.setStudentMail(bookRentalMg.getStudent().getMail());
        bookRentalDto.setStudentName(bookRentalMg.getStudent().getName());
        bookRentalDto.setStudentSurName(bookRentalMg.getStudent().getSurName());
        bookRentalDto.setStudentVerify(bookRentalMg.getStudent().getVerification());
        bookRentalDto.setStudentTcNo(bookRentalMg.getStudent().getTcNo());
        bookRentalDto.setDelivered(bookRentalMg.isDelivered());

        return bookRentalDto;
    }

    public static BookRental toEntity(BookRentalDto bookRentalDto){

        BookRental bookRental = new BookRental();

        bookRental.setDeliveryDate(bookRentalDto.getDeliveryDate());
        bookRental.setEndDate(bookRentalDto.getEndDate());
        bookRental.setDaysRented(bookRentalDto.getDaysRented());
        bookRental.setDelivered(bookRentalDto.isDelivered());
        bookRental.setBook(bookDao.getById(bookRentalDto.getBookId()));
        bookRental.setStudent(studentDao.getById(bookRentalDto.getStudentId()));

        return bookRental;
    }

    public static BookRentalMg toEntityMg(BookRentalDto bookRentalDto){

        BookRentalMg bookRentalMg = new BookRentalMg();

        bookRentalMg.setDeliveryDate(bookRentalDto.getDeliveryDate());
        bookRentalMg.setEndDate(bookRentalDto.getEndDate());
        bookRentalMg.setDaysRented(bookRentalDto.getDaysRented());
        bookRentalMg.setDelivered(bookRentalDto.isDelivered());
        bookRentalMg.setBook(bookMgDao.getByBookId(bookRentalDto.getBookId()));
        bookRentalMg.setStudent(studentMgDao.getByStudentId(bookRentalDto.getStudentId()));

        return bookRentalMg;
    }
}
