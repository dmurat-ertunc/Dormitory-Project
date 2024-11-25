package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "studentTbl")
public class Student extends BaseEntity{

    private String name;
    private String surName;
    private String tcNo;
    private String mail;
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(
            name = "student_university", // ilişkiyi temsil eden ara tablo
            joinColumns = @JoinColumn(name = "student_id"), // öğrenci ile ilgili sütun
            inverseJoinColumns = @JoinColumn(name = "univercity_id") // üniversiteyi ile ilgili sütun
    )
    //@JsonManagedReference
    private Set<University> university = new HashSet<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentalList;
    private boolean verification = false;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentRequestRental> studentRequestRentalList;
    @Column(nullable = true)
    private int score = 100;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntryExit> entryExits;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Punishments> punishments;
    @Column(nullable = true)
    private int remainingPermitHours = 30;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookRental> bookRentals;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MealTime> mealTimes;
    @Column(nullable = true)
    private boolean throwMongo = false;

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
    public boolean isThrowMongo() {
        return throwMongo;
    }
    public void setThrowMongo(boolean throwMongo) {
        this.throwMongo = throwMongo;
    }
}



//@OneToOne(cascade = CascadeType.ALL)   //Bir kullanıcı kaydedildiğinde veya silindiğinde, ilişkili adres entity'si de aynı işlemi geçirecektir.
//@JoinColumn(name = "universityId", referencedColumnName = "id") //Studebt tablosunda, University tablosundaki id sütununu referans alan universityId isimli bir yabancı anahtar oluşturur.
