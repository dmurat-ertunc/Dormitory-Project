package com.dme.DormitoryProject.dtos.punishment;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.punishment.PunishmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunishmentDTO {
    private Long id;
    private LocalTime punishmentTime;
    @NotNull(message = "Ceza alan öğrenci girilmesi zorunlu")
    private Long studentId;
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;
    @NotNull(message = "Ceza türü girilmesi zorunlu")
    private Long punishmentDefinitionsId;
    private String punismentDefinitionsDescription;
    private int punismentDefinitionsPenaltyScore;

    public PunishmentDTO(){

    }

    public PunishmentDTO(Long id, LocalTime punishmentTime,
                         Long studentId, LocalDate studentBirthDate,
                         String studentMail, String studentName, String studentSurName,
                         String studentTcNo, boolean studentVerify, Long punishmentDefinitionsId,
                         String punismentDefinitionsDescription, int punismentDefinitionsPenaltyScore) {
        this.id=id;
        this.punishmentTime = punishmentTime;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
        this.punismentDefinitionsDescription=punismentDefinitionsDescription;
        this.punishmentDefinitionsId=punishmentDefinitionsId;
        this.punismentDefinitionsPenaltyScore=punismentDefinitionsPenaltyScore;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalTime getPunishmentTime() {
        return punishmentTime;
    }
    public void setPunishmentTime(LocalTime punishmentTime) {
        this.punishmentTime = punishmentTime;
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
    public Long getPunishmentDefinitionsId() {
        return punishmentDefinitionsId;
    }
    public void setPunishmentDefinitionsId(Long punishmentDefinitionsId) {
        this.punishmentDefinitionsId = punishmentDefinitionsId;
    }
    public String getPunismentDefinitionsDescription() {
        return punismentDefinitionsDescription;
    }
    public void setPunismentDefinitionsDescription(String punismentDefinitionsDescription) {
        this.punismentDefinitionsDescription = punismentDefinitionsDescription;
    }
    public int getPunismentDefinitionsPenaltyScore() {
        return punismentDefinitionsPenaltyScore;
    }
    public void setPunismentDefinitionsPenaltyScore(int punismentDefinitionsPenaltyScore) {
        this.punismentDefinitionsPenaltyScore = punismentDefinitionsPenaltyScore;
    }
}
