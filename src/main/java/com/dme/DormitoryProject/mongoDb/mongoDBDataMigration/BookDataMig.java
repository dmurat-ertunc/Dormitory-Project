//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Book;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookMgDao;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookRentalMgDao;
//import com.dme.DormitoryProject.repository.IBookDao;
//import com.dme.DormitoryProject.repository.IBookRentalDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BookDataMig {
//
//    private IBookMgDao bookMgDao;
//    private IBookDao bookDao;
//    private IBookRentalMgDao bookRentalMgDao;
//
//    @Autowired
//    public BookDataMig(IBookMgDao bookMgDao, IBookDao bookDao, IBookRentalMgDao bookRentalMgDao) {
//        this.bookMgDao = bookMgDao;
//        this.bookDao = bookDao;
//        this.bookRentalMgDao=bookRentalMgDao;
//    }
//    @Scheduled(cron = "20 00 14 * * ?")
//    public void migration(){
//        List<Book> bookList = bookDao.findAll();
//
//        for (Book book : bookList){
//            if (!book.isThrowMongo()){
//
//                BookMg bookMg = new BookMg();
//
//                bookMg.setBookId(book.getId());
//                bookMg.setName(book.getName());
//                bookMg.setEmpty(book.isEmpty());
//                bookMg.setType(book.getType());
//                bookMg.setDeleted(book.isDeleted());
//                bookMg.setAddDate(book.getAddDate());
//                bookMg.setBookRentals(bookRentalMgDao.findByBookId(book.getId()));
//
//                book.setThrowMongo(true);
//
//                bookDao.save(book);
//                bookMgDao.save(bookMg);
//            }
//        }
//    }
//}
