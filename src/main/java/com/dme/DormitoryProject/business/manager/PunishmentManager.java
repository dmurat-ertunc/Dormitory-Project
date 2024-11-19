package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IPunishmentService;
import com.dme.DormitoryProject.dtos.punishment.PunishmentDTO;
import com.dme.DormitoryProject.dtos.punishment.PunishmentMapper;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunishmentManager extends BaseClass implements IPunishmentService {

    private IPunishmentDao punishmentDao;

    @Autowired
    public PunishmentManager(IPunishmentDao punishmentDao){
        this.punishmentDao=punishmentDao;
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

}
