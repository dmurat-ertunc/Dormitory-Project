package com.dme.DormitoryProject.dtos.staffDtos;

import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.IDepartmentDao;
import com.dme.DormitoryProject.repository.IManagerDao;
import com.dme.DormitoryProject.repository.ITitleDao;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {
    private static IDepartmentDao departmentDao;
    private static IManagerDao managerDao;
    private static ITitleDao titleDao;

    public StaffMapper(IDepartmentDao departmentDao, IManagerDao managerDao, ITitleDao titleDao){
        this.departmentDao=departmentDao;
        this.managerDao=managerDao;
        this.titleDao=titleDao;
    }

    public static StaffDTO toDTO(Staff staff){
        StaffDTO staffDTO = new StaffDTO();

        staffDTO.setId(staff.getId());
        staffDTO.setMail(staff.getMail());
        staffDTO.setName(staff.getName());
        staffDTO.setPhoneNumber(staff.getPhoneNumber());
        staffDTO.setSalary(staff.getSalary());
        staffDTO.setSurName(staff.getSurName());
        staffDTO.setDepartmentId(staff.getDepartment().getId());
        staffDTO.setDepartmentName(staff.getDepartment().getName());
        staffDTO.setTitleId(staff.getTitle().getId());
        staffDTO.setTitleName(staff.getTitle().getName());
        staffDTO.setManagerId(staff.getManager().getId());
        staffDTO.setManagerMail(staff.getManager().getMail());
        staffDTO.setManagerName(staff.getManager().getName());
        staffDTO.setManagerPhoneNumber(staff.getManager().getPhoneNumber());
        staffDTO.setManagerSalary(staff.getManager().getSalary());
        staffDTO.setManagerSurName(staff.getManager().getSurName());

        return staffDTO;
    }
    public static Staff toEntity(StaffDTO staffDTO){
        Staff staff = new Staff();

        staff.setMail(staffDTO.getMail());
        staff.setName(staffDTO.getName());
        staff.setPhoneNumber(staffDTO.getPhoneNumber());
        staff.setSalary(staffDTO.getSalary());
        staff.setSurName(staffDTO.getSurName());
        staff.setDepartment(departmentDao.getById(staffDTO.getDepartmentId()));
        staff.setTitle(titleDao.getById(staffDTO.getTitleId()));
        staff.setManager(managerDao.getById(staffDTO.getManagerId()));

        return staff;
    }
}
