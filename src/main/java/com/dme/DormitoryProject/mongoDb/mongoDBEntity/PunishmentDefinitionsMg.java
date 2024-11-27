package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Punishments;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "punishmentDefinitions")
public class PunishmentDefinitionsMg extends BaseEntityMg{
    private Long punishmentDefinitionsId;
    private int penaltyScore;
    private String description;
    @DBRef
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

    public Long getPunishmentDefinitionsId() {
        return punishmentDefinitionsId;
    }

    public void setPunishmentDefinitionsId(Long punishmentDefinitionsId) {
        this.punishmentDefinitionsId = punishmentDefinitionsId;
    }
}
