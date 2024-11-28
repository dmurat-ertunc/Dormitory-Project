package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.BookRental;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookRentalMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookRentalMgDao;
import com.dme.DormitoryProject.repository.IBookRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRentalDataMig {

    private IBookRentalDao bookRentalDao;
    private IBookRentalMgDao bookRentalMgDao;

    @Autowired
    public BookRentalDataMig(IBookRentalDao bookRentalDao, IBookRentalMgDao bookRentalMgDao) {
        this.bookRentalDao = bookRentalDao;
        this.bookRentalMgDao = bookRentalMgDao;
    }
    @Scheduled(cron = "30 00 14 * * ?")
    public void migration(){
        List<BookRental> bookRentalList = bookRentalDao.findAll();

        for (BookRental bookRental : bookRentalList){
            BookRentalMg bookRentalMg = new BookRentalMg();

            bookRentalMg.setBookRentalId(bookRental.getId());
            bookRentalMg.setBook(bookRental.getBook());
            bookRentalMg.setDelivered(bookRental.isDelivered());
            bookRentalMg.setEndDate(bookRental.getEndDate());
            bookRentalMg.setDaysRented(bookRental.getDaysRented());
            bookRentalMg.setDeliveryDate(bookRental.getDeliveryDate());
            bookRentalMg.setAddDate(bookRental.getAddDate());
            bookRentalMg.setDeleted(bookRental.isDeleted());
            bookRentalMg.setStudent(bookRental.getStudent());

            bookRental.setThrowMongo(true);

            bookRentalMgDao.save(bookRentalMg);
            bookRentalDao.save(bookRental);

        }
    }
}
