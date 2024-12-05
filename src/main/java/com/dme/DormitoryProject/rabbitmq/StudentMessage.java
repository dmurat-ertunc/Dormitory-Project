package com.dme.DormitoryProject.rabbitmq;


public class StudentMessage {

    private Long id;
    private Long studentId;
    private String message;

    public StudentMessage(){

    }

    public StudentMessage(Long id, Long studentId, String message) {
        this.id = id;
        this.studentId = studentId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
