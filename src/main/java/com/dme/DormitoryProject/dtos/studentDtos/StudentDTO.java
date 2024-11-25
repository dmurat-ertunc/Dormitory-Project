package com.dme.DormitoryProject.dtos.studentDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StudentDTO implements Serializable {
    private Long id;
    @NotNull(message = "Öğrenci isim alanı boş bırakılamaz")
    @NotEmpty(message = "Öğrenci isim alanı boş bırakılamaz")
    private String name;
    @NotNull(message = "Öğrenci soyisim alanı boş bırakılamaz")
    @NotEmpty(message = "Öğrenci soyisim alanı boş bırakılamaz")
    private String surName;
    @NotNull(message = "Öğrenci kimlik numarası alanı boş bırakılamaz")
    @NotEmpty(message = "Öğrenci kimlik numarası alanı boş bırakılamaz")
    @Size(min = 11, max = 11, message = "Değer 11 haneli olmalıdır.")
    private String tcNo;
    @NotNull(message = "Öğrenci mail alanı boş bırakılamaz")
    @NotEmpty(message = "Öğrenci mail alanı boş bırakılamaz")
    private String mail;
    @NotNull(message = "Öğrenci doğum tarihi alanı boş bırakılamaz")
    private LocalDate birthDate;
    @NotNull(message = "Öğrenci üniversitesi boş bırakılamaz")
    @NotEmpty(message = "Öğrenci üniversitesi boş bırakılamaz")
    private Set<Long> universityIds = new HashSet<>(); // Üniversite ID'leri
    private Set<String> universityName = new HashSet<>();
    private boolean verify;
    private int score;
    private int remainingPermitHours;
    private Long roomId;
    private Long roomNo;
    private int roomSize;
    private int manySize;
    private float perPersonPrice;
    private boolean isFull;
    private boolean throwMongo;

    public StudentDTO(Long id, String name, String surName, String tcNo, String mail,
                      LocalDate birthDate, Set<Long> universityIds, Set<String> universityName,
                      boolean verify, int score, int remainingPermitHours, Long roomId, int roomSize,
                      int manySize, float perPersonPrice, boolean isFull, Long roomNo, boolean throwMongo) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.tcNo = tcNo;
        this.mail = mail;
        this.birthDate = birthDate;
        this.universityIds = universityIds;
        this.universityName = universityName;
        this.verify = verify;
        this.score = score;
        this.remainingPermitHours = remainingPermitHours;
        this.roomId = roomId;
        this.roomSize = roomSize;
        this.manySize = manySize;
        this.perPersonPrice = perPersonPrice;
        this.isFull = isFull;
        this.roomNo=roomNo;
        this.throwMongo=throwMongo;
    }

    public StudentDTO(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public Set<Long> getUniversityIds() {
        return universityIds;
    }
    public void setUniversityIds(Set<Long> universityIds) {
        this.universityIds = universityIds;
    }
    public Set<String> getUniversityName() {
        return universityName;
    }
    public void setUniversityName(Set<String> universityName) {
        this.universityName = universityName;
    }
    public boolean isVerify() {
        return verify;
    }
    public void setVerify(boolean verify) {
        this.verify = verify;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getRemainingPermitHours() {
        return remainingPermitHours;
    }
    public void setRemainingPermitHours(int remainingPermitHours) {
        this.remainingPermitHours = remainingPermitHours;
    }
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public Long getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
    public int getRoomSize() {
        return roomSize;
    }
    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    public int getManySize() {
        return manySize;
    }
    public void setManySize(int manySize) {
        this.manySize = manySize;
    }
    public float getPerPersonPrice() {
        return perPersonPrice;
    }
    public void setPerPersonPrice(float perPersonPrice) {
        this.perPersonPrice = perPersonPrice;
    }
    public boolean isFull() {
        return isFull;
    }
    public void setFull(boolean full) {
        isFull = full;
    }
    public boolean isThrowMongo() {
        return throwMongo;
    }
    public void setThrowMongo(boolean throwMongo) {
        this.throwMongo = throwMongo;
    }
}
