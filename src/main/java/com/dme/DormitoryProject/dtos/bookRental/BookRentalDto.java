package com.dme.DormitoryProject.dtos.bookRental;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookRentalDto {

    @NotNull(message = "Son tarih alanı boş geçilemez")
    private LocalDate endDate;
    private LocalDate deliveryDate;
    private String daysRented;
    private Long studentId;
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;
    @NotNull(message = "Kitap id alanı boş geçilemez")
    private Long bookId;
    private String bookName;
    private String bookType;
    private boolean delivered;

    public BookRentalDto(){

    }

    public BookRentalDto(LocalDate endDate, LocalDate deliveryDate, String daysRented,
                         Long studentId, LocalDate studentBirthDate, String studentMail,
                         String studentName, String studentSurName, String studentTcNo,
                         boolean studentVerify, Long bookId, String bookName, String bookType, boolean delivered) {
        this.endDate = endDate;
        this.deliveryDate = deliveryDate;
        this.daysRented = daysRented;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookType = bookType;
        this.delivered=delivered;
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
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public LocalDate getStudentBirthDate() {
        return studentBirthDate;
    }
    public void setStudentBirthDate(LocalDate studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }
    public String getStudentMail() {
        return studentMail;
    }
    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentSurName() {
        return studentSurName;
    }
    public void setStudentSurName(String studentSurName) {
        this.studentSurName = studentSurName;
    }
    public String getStudentTcNo() {
        return studentTcNo;
    }
    public void setStudentTcNo(String studentTcNo) {
        this.studentTcNo = studentTcNo;
    }
    public boolean isStudentVerify() {
        return studentVerify;
    }
    public void setStudentVerify(boolean studentVerify) {
        this.studentVerify = studentVerify;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookType() {
        return bookType;
    }
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
    public boolean isDelivered() {
        return delivered;
    }
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
