package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IPunishmentDefinitionsService;
import com.dme.DormitoryProject.dtos.punishmentDefinitions.PunishmentDefinitionsDTO;
import com.dme.DormitoryProject.dtos.punishmentDefinitions.PunishmentDefinitionsMapper;
import com.dme.DormitoryProject.entity.PunishmentDefinitions;
import com.dme.DormitoryProject.repository.IPunishmentDeifinitionsDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunishmentDefinitionsManager extends BaseClass implements IPunishmentDefinitionsService {

    private IPunishmentDeifinitionsDao punishmentDeifinitionsDao;

    @Autowired
    public PunishmentDefinitionsManager(IPunishmentDeifinitionsDao punishmentDeifinitionsDao){
        this.punishmentDeifinitionsDao=punishmentDeifinitionsDao;
    }

    @Override
    public Result getAll() {
        try {
            List<PunishmentDefinitionsDTO> punishmentDefinitionsDTOS = entityToDtoList(punishmentDeifinitionsDao.findAll(), PunishmentDefinitionsMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,punishmentDefinitionsDTOS);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result save(PunishmentDefinitionsDTO punishmentDefinitionsDTO) {
        PunishmentDefinitions punishmentDefinitions = dtoToEntity(punishmentDefinitionsDTO,PunishmentDefinitionsMapper::toEntity);
        punishmentDeifinitionsDao.save(punishmentDefinitions);
        return new SuccesResult(JsonFileReader.getMessage("201","tr"),true);
    }

    @Override
    public Result update(PunishmentDefinitionsDTO punishmentDefinitionsDTO, Long id) {
        PunishmentDefinitions punishmentDefinitions = punishmentDeifinitionsDao.getById(id);
        try {
            punishmentDefinitions.setPenaltyScore(punishmentDefinitionsDTO.getPenaltyScore());
            punishmentDefinitions.setDescription(punishmentDefinitionsDTO.getDescription());
            punishmentDeifinitionsDao.save(punishmentDefinitions);
            return new SuccesResult(JsonFileReader.getMessage("202","tr"),true);
        }catch (Exception e){
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }
}
