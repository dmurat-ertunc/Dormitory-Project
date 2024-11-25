package com.dme.DormitoryProject.dtos.studentGetPermission;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StudentGetPermissionDTO {

    @NotNull(message = "İzin başlangıç saati boş bırakılamaz")
    private LocalDate startDate;
    @NotNull(message = "İzin bitiş saati boş bırakılamaz")
    private LocalDate endDate;
    private boolean approval;
    private Long studentId;
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;
    private Long id;

    public StudentGetPermissionDTO(){

    }

    public StudentGetPermissionDTO(LocalDate startDate, LocalDate endDate, boolean approval,
                                   Long studentId, LocalDate studentBirthDate, String studentMail,
                                   String studentName, String studentSurName, String studentTcNo,
                                   boolean studentVerify, Long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.approval = approval;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
        this.id=id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public boolean isApproval() {
        return approval;
    }
    public void setApproval(boolean approval) {
        this.approval = approval;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
