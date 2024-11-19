package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMailDao extends JpaRepository<Long, Mail> {
}
