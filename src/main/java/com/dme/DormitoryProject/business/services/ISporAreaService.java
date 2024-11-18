package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.response.Result;

public interface ISporAreaService {
    Result getAll();
    Result getById(Long id);
    Result saveSportArea(SportAreaDTO sportAreaDTO);
    Result updateSportArea(Long id,SportAreaDTO sportAreaDTO);
    Result deleteSportArea(Long id);

}
