package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.LogLevel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mail")
public class MailMg extends BaseEntityMg{
    private Long mailId;
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
    public Long getMailId() {
        return mailId;
    }
    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }
}
