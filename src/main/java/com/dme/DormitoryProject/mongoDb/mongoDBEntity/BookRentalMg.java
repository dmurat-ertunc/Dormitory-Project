package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "bookRental")
public class BookRentalMg extends BaseEntityMg {
    private Long bookRentalId;
    private LocalDate endDate;
    private LocalDate deliveryDate;
    private String daysRented;
    @DBRef
    private Student student;
    @DBRef
    private Book book;
    private boolean delivered;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getDaysRented() {
        return daysRented;
    }
    public void setDaysRented(String daysRented) {
        this.daysRented = daysRented;
    }
    public boolean isDelivered() {
        return delivered;
    }
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    public Long getBookRentalId() {
        return bookRentalId;
    }
    public void setBookRentalId(Long bookRentalId) {
        this.bookRentalId = bookRentalId;
    }
}