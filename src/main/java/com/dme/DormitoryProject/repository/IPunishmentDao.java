package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Punishments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPunishmentDao extends JpaRepository<Punishments,Long> {
}
