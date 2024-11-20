package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "punishmentDefinitionsTbl")
public class PunishmentDefinitions extends BaseEntity{

    private int penaltyScore;
    private String description;
    @OneToMany(mappedBy = "punishmentDefinitions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Punishments> punishments;

    public int getPenaltyScore() {
        return penaltyScore;
    }
    public void setPenaltyScore(int penaltyScore) {
        this.penaltyScore = penaltyScore;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Punishments> getPunishments() {
        return punishments;
    }
    public void setPunishments(List<Punishments> punishments) {
        this.punishments = punishments;
    }
}
