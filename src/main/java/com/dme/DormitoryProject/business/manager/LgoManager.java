package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.ILgoService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoDTO;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoMapper;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.repository.ILgoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LgoManager extends BaseClass implements ILgoService {
    private ILgoDao lgoDao;

    @Autowired
    public LgoManager(ILgoDao lgoDao){
        this.lgoDao=lgoDao;
    }

    @Override
    public List<LgoDTO> getAll(){
        return entityToDtoList(lgoDao.findAll(),LgoMapper::toDTO);
    }
    @Override
    public Lgo getById(Long id){
        return  lgoDao.findLgoById(id);
    }
    @Override
    public Lgo saveLgo(Lgo lgo){
        LocalDate date = LocalDate.now();
        lgo.setAddDate(date);
        return lgoDao.save(lgo);
    }
}
