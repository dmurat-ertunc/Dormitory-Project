package com.dme.DormitoryProject.dtos.punishment;

import com.dme.DormitoryProject.entity.Punishments;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PunishmentMapper {

    @Autowired
    private static IStudentDao studentDao;

    public PunishmentMapper(IStudentDao studentDao){
        this.studentDao=studentDao;
    }

    public static PunishmentDTO toDto(Punishments punishments){

        PunishmentDTO punishmentDTO = new PunishmentDTO();

        punishmentDTO.setId(punishments.getId());
        punishmentDTO.setPunishmentTime(punishments.getPunishmentTime());
        punishmentDTO.setPunishmentType(punishments.getPunishmentType());
        punishmentDTO.setPenaltyScore(punishments.getPenaltyScore());
        punishmentDTO.setStudentId(punishments.getStudent().getId());
        punishmentDTO.setStudentMail(punishments.getStudent().getMail());
        punishmentDTO.setStudentName(punishments.getStudent().getName());
        punishmentDTO.setStudentSurName(punishments.getStudent().getSurName());
        punishmentDTO.setStudentTcNo(punishments.getStudent().getTcNo());
        punishmentDTO.setStudentVerify(punishments.getStudent().getVerification());
        punishmentDTO.setStudentBirthDate(punishments.getStudent().getBirthDate());

        return punishmentDTO;
    }

    public static Punishments toEntity(PunishmentDTO punishmentDTO){

        Punishments punishments = new Punishments();

        punishments.setPunishmentTime(punishmentDTO.getPunishmentTime());
        punishments.setPunishmentType(punishmentDTO.getPunishmentType());
        punishments.setPenaltyScore(punishmentDTO.getPenaltyScore());
        punishments.setStudent(studentDao.findStudentById(punishmentDTO.getStudentId()));

        return punishments;
    }
}
