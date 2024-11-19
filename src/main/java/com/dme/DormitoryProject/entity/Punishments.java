package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.punishment.PunishmentType;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "punishmentTbl")
public class Punishments extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private PunishmentType punishmentType;
    private LocalTime punishmentTime;
    private int penaltyScore;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

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
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
