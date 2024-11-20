package com.dme.DormitoryProject.dtos.punishmentDefinitions;

import com.dme.DormitoryProject.entity.PunishmentDefinitions;

public class PunishmentDefinitionsMapper {

    public static PunishmentDefinitionsDTO toDto(PunishmentDefinitions punishmentDefinitions){
        PunishmentDefinitionsDTO punishmentDefinitionsDTO = new PunishmentDefinitionsDTO();

        punishmentDefinitionsDTO.setId(punishmentDefinitions.getId());
        punishmentDefinitionsDTO.setDescription(punishmentDefinitions.getDescription());
        punishmentDefinitionsDTO.setPenaltyScore(punishmentDefinitions.getPenaltyScore());

        return punishmentDefinitionsDTO;
    }

    public static PunishmentDefinitions toEntity(PunishmentDefinitionsDTO punishmentDefinitionsDTO){
        PunishmentDefinitions punishmentDefinitions = new PunishmentDefinitions();

        punishmentDefinitions.setDescription(punishmentDefinitionsDTO.getDescription());
        punishmentDefinitions.setPenaltyScore(punishmentDefinitionsDTO.getPenaltyScore());

        return punishmentDefinitions;
    }
}
