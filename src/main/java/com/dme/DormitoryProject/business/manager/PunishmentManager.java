package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IPunishmentService;
import com.dme.DormitoryProject.dtos.punishment.PunishmentDTO;
import com.dme.DormitoryProject.dtos.punishment.PunishmentMapper;
import com.dme.DormitoryProject.entity.PunishmentDefinitions;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import com.dme.DormitoryProject.repository.IPunishmentDeifinitionsDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import io.swagger.v3.core.util.Json;
import org.hibernate.boot.archive.spi.ArchiveEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class PunishmentManager extends BaseClass implements IPunishmentService {

    private IPunishmentDao punishmentDao;
    private IPunishmentDeifinitionsDao punishmentDeifinitionsDao;
    private IStudentDao studentDao;

    @Autowired
    public PunishmentManager(IStudentDao studentDao,IPunishmentDao punishmentDao, IPunishmentDeifinitionsDao punishmentDeifinitionsDao){
        this.punishmentDao=punishmentDao;
        this.punishmentDeifinitionsDao=punishmentDeifinitionsDao;
        this.studentDao=studentDao;
    }

    @Override
    public Result getAll(){
        try {
            List<PunishmentDTO> punishmentDTOList = entityToDtoList(punishmentDao.findAll(), PunishmentMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,punishmentDTOList);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result save(PunishmentDTO punishmentDTO) {
        try {
            Student student = studentDao.getById(punishmentDTO.getStudentId());
            PunishmentDefinitions punishmentDefinitions = punishmentDeifinitionsDao.getById(punishmentDTO.getPunishmentDefinitionsId());
            student.setScore(student.getScore() - punishmentDefinitions.getPenaltyScore());
            studentDao.save(student);
            punishmentDTO.setPunishmentTime(LocalTime.now());
            punishmentDao.save(dtoToEntity(punishmentDTO, PunishmentMapper::toEntity));
            return new SuccesResult(JsonFileReader.getMessage("201","tr"),true);
        } catch (Exception e) {
            return new Result(JsonFileReader.getMessage("501","tr"),false);
        }
    }
}
