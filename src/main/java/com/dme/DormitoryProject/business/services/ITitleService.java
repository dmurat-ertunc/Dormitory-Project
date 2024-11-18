package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.response.Result;

public interface ITitleService {
    Result getAll();
    Result getById(Long id);
    Result saveTitle(TitleDTO titleDTO);
    Result updateTitle(Long id, TitleDTO titleDTO);
    Result deleteTitle(Long id);
}
