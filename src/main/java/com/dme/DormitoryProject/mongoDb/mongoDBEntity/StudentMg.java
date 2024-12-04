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
    private Set<UniversityMg> university = new HashSet<>();
    @DBRef
    private List<RentalMg> rentalList;
    private boolean verification = false;
    @DBRef
    private List<StudentRequestRentalMg> studentRequestRentalList;
    @Column(nullable = true)
    private int score = 100;
    @DBRef
    private List<EntryExitMg> entryExits;
    @DBRef
    private List<PunishmentsMg> punishments;
    @Column(nullable = true)
    private int remainingPermitHours = 30;
    @DBRef
    private List<BookRentalMg> bookRentals;
    @DBRef
    private RoomMg room;
    @DBRef
    private List<MealTimeMg> mealTimes;
    @DBRef
    private List<StudentGetPermissionMg> studentGetPermissions;

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
    public Set<UniversityMg> getUniversity() {
        return university;
    }
    public void setUniversity(Set<UniversityMg> university) {
        this.university = university;
    }
    public List<RentalMg> getRentalList() {
        return rentalList;
    }
    public void setRentalList(List<RentalMg> rentalList) {
        this.rentalList = rentalList;
    }
    public boolean getVerification() {
        return verification;
    }
    public void setVerification(boolean verification) {
        this.verification = verification;
    }
    public List<StudentRequestRentalMg> getStudentRequestRentalList() {
        return studentRequestRentalList;
    }
    public void setStudentRequestRentalList(List<StudentRequestRentalMg> studentRequestRentalList) {
        this.studentRequestRentalList = studentRequestRentalList;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public List<EntryExitMg> getEntryExits() {
        return entryExits;
    }
    public void setEntryExits(List<EntryExitMg> entryExits) {
        this.entryExits = entryExits;
    }
    public List<PunishmentsMg> getPunishments() {
        return punishments;
    }
    public void setPunishments(List<PunishmentsMg> punishments) {
        this.punishments = punishments;
    }
    public int getRemainingPermitHours() {
        return remainingPermitHours;
    }
    public void setRemainingPermitHours(int remainingPermitHours) {
        this.remainingPermitHours = remainingPermitHours;
    }
    public List<BookRentalMg> getBookRentals() {
        return bookRentals;
    }
    public void setBookRentals(List<BookRentalMg> bookRentals) {
        this.bookRentals = bookRentals;
    }
    public RoomMg getRoom() {
        return room;
    }
    public void setRoom(RoomMg room) {
        this.room = room;
    }
    public List<MealTimeMg> getMealTimes() {
        return mealTimes;
    }
    public void setMealTimes(List<MealTimeMg> mealTimes) {
        this.mealTimes = mealTimes;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<StudentGetPermissionMg> getStudentGetPermissions() {
        return studentGetPermissions;
    }

    public void setStudentGetPermissions(List<StudentGetPermissionMg> studentGetPermissions) {
        this.studentGetPermissions = studentGetPermissions;
    }
}
