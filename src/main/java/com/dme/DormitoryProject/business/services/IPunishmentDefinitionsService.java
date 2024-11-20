package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.punishmentDefinitions.PunishmentDefinitionsDTO;
import com.dme.DormitoryProject.response.Result;

public interface IPunishmentDefinitionsService {
    Result getAll();
    Result save(PunishmentDefinitionsDTO punishmentDefinitionsDTO);
    Result update(PunishmentDefinitionsDTO punishmentDefinitionsDTO, Long id);

}
