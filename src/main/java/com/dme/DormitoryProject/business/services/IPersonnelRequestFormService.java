package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.response.Result;

public interface IPersonnelRequestFormService {
    Result getAllPersonnelRequest();
    Result acceptPersonnelRequest(Long id);
}
