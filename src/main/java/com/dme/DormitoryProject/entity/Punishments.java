package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.punishment.PunishmentType;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "punishmentTbl")
public class Punishments extends BaseEntity{
    private LocalTime punishmentTime;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "punismentDefinitionsId")
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
}
