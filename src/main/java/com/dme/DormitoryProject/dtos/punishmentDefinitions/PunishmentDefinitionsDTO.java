package com.dme.DormitoryProject.dtos.punishmentDefinitions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PunishmentDefinitionsDTO {
    private Long id;
    @NotNull(message = "Ceza değeri alanı boş geçilemez")
    private int penaltyScore;
    @NotNull(message = "Ceza açıklaması alanı boş geçilemez")
    @NotEmpty(message = "Ceza açıklaması alanı boş geçilemez")
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
