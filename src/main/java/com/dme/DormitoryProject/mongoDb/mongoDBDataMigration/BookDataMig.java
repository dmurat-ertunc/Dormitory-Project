package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
import com.dme.DormitoryProject.repository.IBookDao;
import com.dme.DormitoryProject.repository.IBookRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDataMig {

    private IBookMgDao bookMgDao;
    private IBookDao bookDao;
    private IBookRentalDao bookRentalDao;

    @Autowired
    public BookDataMig(IBookMgDao bookMgDao, IBookDao bookDao, IBookRentalDao bookRentalDao) {
        this.bookMgDao = bookMgDao;
        this.bookDao = bookDao;
        this.bookRentalDao=bookRentalDao;
    }
    @Scheduled(cron = "20 00 14 * * ?")
    public void migration(){
        List<Book> bookList = bookDao.findAll();

        for (Book book : bookList){
            if (!book.isThrowMongo()){

                BookMg bookMg = new BookMg();

                bookMg.setBookId(book.getId());
                bookMg.setName(book.getName());
                bookMg.setEmpty(book.isEmpty());
                bookMg.setType(book.getType());
                bookMg.setDeleted(book.isDeleted());
                bookMg.setAddDate(book.getAddDate());
                bookMg.setBookRentals(bookRentalDao.findByBookId(book.getId()));

                book.setThrowMongo(true);

                bookDao.save(book);
                bookMgDao.save(bookMg);
            }
        }
    }
}
