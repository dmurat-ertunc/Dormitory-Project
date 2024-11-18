package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.response.Result;

public interface IPersonnelRequestFormService {
    Result getAllPersonnelRequest();
    Result acceptPersonnelRequest(Long id);
}
