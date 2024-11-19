package com.dme.DormitoryProject.dtos.entryExit;

import com.dme.DormitoryProject.entity.EntryExit;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.stereotype.Component;

@Component
public class EntryExitMapper {

    private static IStudentDao studentDao;

    public EntryExitMapper(IStudentDao studentDao){
        this.studentDao=studentDao;
    }

    public static EntryExit toEntity(EntryExitDTO entryExitDTO){
        EntryExit entryExit = new EntryExit();

        entryExit.setEntryExit(entryExitDTO.getEntryOrExit());
        entryExit.setMomentaryTime(entryExitDTO.getMomentaryTime());
        entryExit.setAddDate(entryExitDTO.getDate());
        entryExit.setStudent(studentDao.getById(entryExitDTO.getStudentId()));

        return entryExit;
    }

    public static EntryExitDTO toDto(EntryExit entryExit){
        EntryExitDTO entryExitDTO = new EntryExitDTO();

        entryExitDTO.setId(entryExit.getId());
        entryExitDTO.setEntryOrExit(entryExit.getEntryExit());
        entryExitDTO.setMomentaryTime(entryExit.getMomentaryTime());
        entryExitDTO.setDate(entryExit.getAddDate());
        entryExitDTO.setStudentId(entryExit.getStudent().getId());
        entryExitDTO.setStudentBirthDate(entryExit.getStudent().getBirthDate());
        entryExitDTO.setStudentMail(entryExit.getStudent().getMail());
        entryExitDTO.setStudentName(entryExit.getStudent().getName());
        entryExitDTO.setStudentSurName(entryExit.getStudent().getSurName());

        return entryExitDTO;
    }
}
