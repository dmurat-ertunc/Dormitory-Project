package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Punishments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPunishmentDao extends JpaRepository<Punishments,Long> {
    List<Punishments> findByStudentId(Long id);
    List<Punishments> findByPunishmentDefinitionsId(Long id);

}
