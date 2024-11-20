package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.dtos.punishmentDefinitions.PunishmentDefinitionsDTO;
import com.dme.DormitoryProject.entity.PunishmentDefinitions;
import com.dme.DormitoryProject.entity.Punishments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPunishmentDeifinitionsDao extends JpaRepository<PunishmentDefinitions,Long> {
}
