package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IPersonnelRequestFormService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormDTO;
import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormMapper;
import com.dme.DormitoryProject.entity.Mail;
import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import com.dme.DormitoryProject.enums.RequestStatus;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnelRequestFormManager extends BaseClass implements IPersonnelRequestFormService {
    private IPersonelRequestFormDao personelRequestFormDao;
    private IDepartmentDao departmentDao;
    private IManagerDao managerDao;
    private ITitleDao titleDao;
    private IMailDao mailDao;

    @Autowired
    public PersonnelRequestFormManager(IPersonelRequestFormDao personelRequestFormDao, IDepartmentDao departmentDao,
                                       IManagerDao managerDao, ITitleDao titleDao, IMailDao mailDao){
        this.personelRequestFormDao=personelRequestFormDao;
        this.managerDao=managerDao;
        this.departmentDao=departmentDao;
        this.titleDao=titleDao;
        this.mailDao=mailDao;
    }

    @Override
    public Result getAllPersonnelRequest(){
        try{
            List<PersonelRequestFormDTO> personelRequestFormDTOs =
                    entityToDtoList(personelRequestFormDao.findAll(),PersonelRequestFormMapper::toDTO);
            return new SuccessDataResult("Tüm istekler listelendi",true,personelRequestFormDTOs);
        } catch (Exception e) {
            return new ErrorResult("Bir hata oluştu",false);
        }
    }
    @Override
    public Result acceptPersonnelRequest(Long id){
        try {
            PersonnelRequestForm personnelRequestForm = personelRequestFormDao.getById(id);
            Mail mail = new Mail();

            personnelRequestForm.setStatus(RequestStatus.Approved);
            mail.setFromMail("cengdme@gmail.com");
            mail.setToMail(personnelRequestForm.getRequestorManager().getMail());
            mail.setSubject("İstek Talep Bilgilendirme");
            mail.setText(String.valueOf(personnelRequestForm.getDepartment().getName() + " departmanına "
            + personnelRequestForm.getTitle().getName() + " ünvanlı çalışan talebiniz onaylanmıştır"));

            sendMail(mail);
            personelRequestFormDao.save(personnelRequestForm);
            return new SuccesResult("İstek onaylandı, istek sahibine mail olarak bildirildi.",true);
        } catch (Exception e) {
            return new ErrorResult("Bir hata oluştu",false);
        }
    }
}
