package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.*;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "student")
public class StudentMg extends BaseEntityMg{
    private Long studentId;
    private String name;
    private String surName;
    private String tcNo;
    private String mail;
    private LocalDate birthDate;
    @DBRef
    private Set<University> university = new HashSet<>();
    @DBRef
    private List<Rental> rentalList;
    private boolean verification = false;
    @DBRef
    private List<StudentRequestRental> studentRequestRentalList;
    @Column(nullable = true)
    private int score = 100;
    @DBRef
    private List<EntryExit> entryExits;
    @DBRef
    private List<Punishments> punishments;
    @Column(nullable = true)
    private int remainingPermitHours = 30;
    @DBRef
    private List<BookRental> bookRentals;
    @DBRef
    private Room room;
    @DBRef
    private List<MealTime> mealTimes;
    @DBRef
    private List<StudentGetPermission> studentGetPermissions;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getTcNo() {
        return tcNo;
    }
    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public Set<University> getUniversity() {
        return university;
    }
    public void setUniversity(Set<University> university) {
        this.university = university;
    }
    public List<Rental> getRentalList() {
        return rentalList;
    }
    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
    public boolean getVerification() {
        return verification;
    }
    public void setVerification(boolean verification) {
        this.verification = verification;
    }
    public List<StudentRequestRental> getStudentRequestRentalList() {
        return studentRequestRentalList;
    }
    public void setStudentRequestRentalList(List<StudentRequestRental> studentRequestRentalList) {
        this.studentRequestRentalList = studentRequestRentalList;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public List<EntryExit> getEntryExits() {
        return entryExits;
    }
    public void setEntryExits(List<EntryExit> entryExits) {
        this.entryExits = entryExits;
    }
    public List<Punishments> getPunishments() {
        return punishments;
    }
    public void setPunishments(List<Punishments> punishments) {
        this.punishments = punishments;
    }
    public int getRemainingPermitHours() {
        return remainingPermitHours;
    }
    public void setRemainingPermitHours(int remainingPermitHours) {
        this.remainingPermitHours = remainingPermitHours;
    }
    public List<BookRental> getBookRentals() {
        return bookRentals;
    }
    public void setBookRentals(List<BookRental> bookRentals) {
        this.bookRentals = bookRentals;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public List<MealTime> getMealTimes() {
        return mealTimes;
    }
    public void setMealTimes(List<MealTime> mealTimes) {
        this.mealTimes = mealTimes;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<StudentGetPermission> getStudentGetPermissions() {
        return studentGetPermissions;
    }

    public void setStudentGetPermissions(List<StudentGetPermission> studentGetPermissions) {
        this.studentGetPermissions = studentGetPermissions;
    }
}
