package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.response.Result;

public interface IStudentGetPermissionService {
    Result getStudentPermission();
    Result acceptStudentPermission(Long id);

}
