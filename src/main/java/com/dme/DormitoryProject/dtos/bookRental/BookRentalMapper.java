package com.dme.DormitoryProject.dtos.bookRental;

import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.entity.BookRental;
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

    public BookRentalMapper(IStudentDao studentDao, IBookDao bookDao){
        this.bookDao=bookDao;
        this.studentDao=studentDao;
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
}
