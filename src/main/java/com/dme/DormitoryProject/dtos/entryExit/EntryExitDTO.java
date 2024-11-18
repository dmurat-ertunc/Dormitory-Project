package com.dme.DormitoryProject.dtos.entryExit;

import com.dme.DormitoryProject.enums.EntryExit.EntryOrExit;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntryExitDTO {
    private Long id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private EntryOrExit entryOrExit;
    private LocalTime momentaryTime;
    private Long studentId;
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;

    public EntryExitDTO() {

    }

    public EntryExitDTO(LocalDate date, EntryOrExit entryOrExit, LocalTime momentaryTime,
                        Long studentId, LocalDate studentBirthDate, String studentMail,
                        String studentName, String studentSurName, String studentTcNo,
                        boolean studentVerify, Long id) {
        this.date = date;
        this.entryOrExit = entryOrExit;
        this.momentaryTime = momentaryTime;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
        this.id=id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public EntryOrExit getEntryOrExit() {
        return entryOrExit;
    }
    public void setEntryOrExit(EntryOrExit entryOrExit) {
        this.entryOrExit = entryOrExit;
    }
    public LocalTime getMomentaryTime() {
        return momentaryTime;
    }
    public void setMomentaryTime(LocalTime momentaryTime) {
        this.momentaryTime = momentaryTime;
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
