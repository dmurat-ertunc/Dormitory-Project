package com.dme.DormitoryProject.dtos.punishment;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.punishment.PunishmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunishmentDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private PunishmentType punishmentType;
    private LocalTime punishmentTime;
    private int penaltyScore;
    private Long studentId;
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;

    public PunishmentDTO(){

    }

    public PunishmentDTO(Long id,PunishmentType punishmentType, LocalTime punishmentTime,
                         int penaltyScore, Long studentId, LocalDate studentBirthDate,
                         String studentMail, String studentName, String studentSurName,
                         String studentTcNo, boolean studentVerify) {
        this.id=id;
        this.punishmentType = punishmentType;
        this.punishmentTime = punishmentTime;
        this.penaltyScore = penaltyScore;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PunishmentType getPunishmentType() {
        return punishmentType;
    }
    public void setPunishmentType(PunishmentType punishmentType) {
        this.punishmentType = punishmentType;
    }
    public LocalTime getPunishmentTime() {
        return punishmentTime;
    }
    public void setPunishmentTime(LocalTime punishmentTime) {
        this.punishmentTime = punishmentTime;
    }
    public int getPenaltyScore() {
        return penaltyScore;
    }
    public void setPenaltyScore(int penaltyScore) {
        this.penaltyScore = penaltyScore;
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
}
