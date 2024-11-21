package com.dme.DormitoryProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "bookRentalTbl")
public class BookRental extends BaseEntity {

    private LocalDate endDate;
    private LocalDate deliveryDate;
    private String daysRented;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "bookId")
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
}
