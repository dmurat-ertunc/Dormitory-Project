package com.dme.DormitoryProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mailTbl")
public class Mail extends BaseEntity{

    private String fromMail;
    private String toMail;
    private String text;
    private String subject;

    public String getFromMail() {
        return fromMail;
    }
    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }
    public String getToMail() {
        return toMail;
    }
    public void setToMail(String toMail) {
        this.toMail = toMail;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
