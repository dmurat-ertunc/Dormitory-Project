package com.dme.DormitoryProject.rabbitmq;


import java.io.Serializable;

public class StudentMessage implements Serializable {

    private Long studentId;
    private String message;

    public StudentMessage(){

    }

    public StudentMessage(Long studentId, String message) {

        this.studentId = studentId;
        this.message = message;
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
