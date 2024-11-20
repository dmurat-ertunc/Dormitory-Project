package com.dme.DormitoryProject.dtos.punishmentDefinitions;

public class PunishmentDefinitionsDTO {
    private Long id;
    private int penaltyScore;
    private String description;

    public PunishmentDefinitionsDTO(int penaltyScore, String description,Long id) {
        this.penaltyScore = penaltyScore;
        this.description = description;
        this.id=id;
    }
    public PunishmentDefinitionsDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
