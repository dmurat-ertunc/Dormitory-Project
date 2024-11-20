package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.punishment.PunishmentDTO;
import com.dme.DormitoryProject.response.Result;

public interface IPunishmentService {
    Result getAll();
    Result save(PunishmentDTO punishmentDTO);
}
