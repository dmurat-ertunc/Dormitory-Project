package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.PunishmentDefinitions;
import com.dme.DormitoryProject.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "punishment")
public class PunishmentsMg extends BaseEntityMg{
    private Long punishmentId;
    private LocalTime punishmentTime;
    @DBRef
    private Student student;
    @DBRef
    private PunishmentDefinitions punishmentDefinitions;

    public LocalTime getPunishmentTime() {
        return punishmentTime;
    }
    public void setPunishmentTime(LocalTime punishmentTime) {
        this.punishmentTime = punishmentTime;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public PunishmentDefinitions getPunishmentDefinitions() {
        return punishmentDefinitions;
    }
    public void setPunishmentDefinitions(PunishmentDefinitions punishmentDefinitions) {
        this.punishmentDefinitions = punishmentDefinitions;
    }

    public Long getPunishmentId() {
        return punishmentId;
    }

    public void setPunishmentId(Long punishmentId) {
        this.punishmentId = punishmentId;
    }
}
