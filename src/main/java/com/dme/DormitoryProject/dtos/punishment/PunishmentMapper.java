package com.dme.DormitoryProject.dtos.punishment;

import com.dme.DormitoryProject.entity.Punishments;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import com.dme.DormitoryProject.repository.IPunishmentDeifinitionsDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PunishmentMapper {

    @Autowired
    private static IStudentDao studentDao;
    @Autowired
    public static IPunishmentDeifinitionsDao punishmentDeifinitionsDao;

    public PunishmentMapper(IStudentDao studentDao, IPunishmentDeifinitionsDao punishmentDeifinitionsDao){
        this.studentDao=studentDao;
        this.punishmentDeifinitionsDao=punishmentDeifinitionsDao;
    }

    public static PunishmentDTO toDto(Punishments punishments){

        PunishmentDTO punishmentDTO = new PunishmentDTO();

        punishmentDTO.setId(punishments.getId());
        punishmentDTO.setPunishmentTime(punishments.getPunishmentTime());
        punishmentDTO.setStudentId(punishments.getStudent().getId());
        punishmentDTO.setStudentMail(punishments.getStudent().getMail());
        punishmentDTO.setStudentName(punishments.getStudent().getName());
        punishmentDTO.setStudentSurName(punishments.getStudent().getSurName());
        punishmentDTO.setStudentTcNo(punishments.getStudent().getTcNo());
        punishmentDTO.setStudentVerify(punishments.getStudent().getVerification());
        punishmentDTO.setStudentBirthDate(punishments.getStudent().getBirthDate());
        punishmentDTO.setPunishmentDefinitionsId(punishments.getPunishmentDefinitions().getId());
        punishmentDTO.setPunismentDefinitionsDescription(punishments.getPunishmentDefinitions().getDescription());
        punishmentDTO.setPunismentDefinitionsPenaltyScore(punishments.getPunishmentDefinitions().getPenaltyScore());

        return punishmentDTO;
    }

    public static Punishments toEntity(PunishmentDTO punishmentDTO){

        Punishments punishments = new Punishments();

        punishments.setPunishmentTime(punishmentDTO.getPunishmentTime());
        punishments.setStudent(studentDao.findStudentById(punishmentDTO.getStudentId()));
        punishments.setPunishmentDefinitions(punishmentDeifinitionsDao.getById(punishmentDTO.getPunishmentDefinitionsId()));

        return punishments;
    }
}
