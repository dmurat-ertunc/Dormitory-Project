package com.dme.DormitoryProject.annotations;

import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.IStaffDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public final class UniqueMailStaffValidator implements ConstraintValidator<UniqueMailStaff,String> {
    private final IStaffDao staffDao;

    public UniqueMailStaffValidator(IStaffDao staffDao) {
        this.staffDao = staffDao;
    }
    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context){
        List<Staff> staffs = staffDao.findAll();
        for (Staff staff : staffs){
            if(Objects.equals(staff.getMail(),mail)){
                return false;
            }
        }
        return true;
    }

}
