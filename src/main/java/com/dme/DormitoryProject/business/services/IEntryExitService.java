package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.response.Result;

public interface IEntryExitService {
    Result entry(Long id);
    Result exit(Long id);
    Result getAll();
}
