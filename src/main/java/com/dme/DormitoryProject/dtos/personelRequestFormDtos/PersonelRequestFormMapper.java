package com.dme.DormitoryProject.dtos.personelRequestFormDtos;

import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import com.dme.DormitoryProject.repository.IDepartmentDao;
import com.dme.DormitoryProject.repository.IManagerDao;
import com.dme.DormitoryProject.repository.ITitleDao;
import org.springframework.stereotype.Component;

@Component
public class PersonelRequestFormMapper {
    private static IDepartmentDao departmentDao;
    private static IManagerDao managerDao;
    private static ITitleDao titleDao;

    public PersonelRequestFormMapper(IDepartmentDao departmentDao, IManagerDao managerDao, ITitleDao titleDao) {
        this.managerDao=managerDao;
        this.titleDao=titleDao;
        this.departmentDao=departmentDao;
    }

    public static PersonelRequestFormDTO toDTO(PersonnelRequestForm personnelRequestForm){
        PersonelRequestFormDTO personelRequestFormDTO = new PersonelRequestFormDTO();

        personelRequestFormDTO.setDescription(personnelRequestForm.getDescription());
        personelRequestFormDTO.setStatus(String.valueOf(personnelRequestForm.getStatus()));
        personelRequestFormDTO.setDepartmentId(personnelRequestForm.getDepartment().getId());
        personelRequestFormDTO.setDepartmentName(personnelRequestForm.getDepartment().getName());
        personelRequestFormDTO.setTitleId(personnelRequestForm.getTitle().getId());
        personelRequestFormDTO.setTitleName(personnelRequestForm.getTitle().getName());
        personelRequestFormDTO.setManagerId(personnelRequestForm.getManager().getId());
        personelRequestFormDTO.setManagerMail(personnelRequestForm.getManager().getMail());
        personelRequestFormDTO.setManagerName(personnelRequestForm.getManager().getName());
        personelRequestFormDTO.setManagerPhoneNumber(personnelRequestForm.getManager().getPhoneNumber());
        personelRequestFormDTO.setManagerSalary(personnelRequestForm.getManager().getSalary());
        personelRequestFormDTO.setManagerSurName(personnelRequestForm.getManager().getSurName());
        personelRequestFormDTO.setRequestorManagerId(personnelRequestForm.getRequestorManager().getId());
        personelRequestFormDTO.setRequestorManagerMail(personnelRequestForm.getRequestorManager().getMail());
        personelRequestFormDTO.setRequestorManagerName(personnelRequestForm.getRequestorManager().getName());
        personelRequestFormDTO.setRequestorManagerPhoneNumber(personnelRequestForm.getRequestorManager().getPhoneNumber());
        personelRequestFormDTO.setRequestorManagerSalary(personnelRequestForm.getRequestorManager().getSalary());
        personelRequestFormDTO.setRequestorManagerSurName(personnelRequestForm.getRequestorManager().getSurName());

        return personelRequestFormDTO;
    }
    public static PersonnelRequestForm toEntity(PersonelRequestFormDTO personelRequestFormDTO){
        PersonnelRequestForm personnelRequestForm = new PersonnelRequestForm();

        personnelRequestForm.setDescription(personelRequestFormDTO.getDescription());
        personnelRequestForm.setDepartment(departmentDao.getById(personelRequestFormDTO.getDepartmentId()));
        personnelRequestForm.setManager(managerDao.getById(personelRequestFormDTO.getManagerId()));
        personnelRequestForm.setTitle(titleDao.getById(personelRequestFormDTO.getTitleId()));
        personnelRequestForm.setRequestorManager(managerDao.getById(personelRequestFormDTO.getRequestorManagerId()));

        return personnelRequestForm;
    }
}
