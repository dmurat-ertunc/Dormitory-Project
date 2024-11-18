package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IStaffService;
import com.dme.DormitoryProject.business.services.IUserService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormDTO;
import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormMapper;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffManager extends BaseClass implements IStaffService {

    private IStaffDao staffDao;
    private ITitleDao titleDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IDepartmentDao departmentDao;
    private IManagerDao managerDao;
    private IUserService userService;
    private IPersonelRequestFormDao personelRequestFormDao;
    private IUserDao userDao;

    @Autowired
    public StaffManager(IStaffDao staffDao, ILgoDao lgoDao, ILogLevelDao logLevelDao,
                        IDepartmentDao departmentDao, IManagerDao managerDao, ITitleDao titleDao,
                        IUserService userService, IPersonelRequestFormDao personelRequestFormDao,
                        IUserDao userDao) {
        this.staffDao = staffDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.departmentDao = departmentDao;
        this.managerDao = managerDao;
        this.titleDao = titleDao;
        this.userService=userService;
        this.personelRequestFormDao=personelRequestFormDao;
        this.userDao=userDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        lgoDao.save(log);
    }

    @Override
    public Result getAll(){
        try {
            List<StaffDTO> staffDTOS = entityToDtoList(staffDao.findAll(),StaffMapper::toDTO);
            LogLevelSave(2,"Tüm personeller listelendi");
            return new SuccessDataResult("Tüm personeller listelendi",true,staffDTOS);
        } catch (Exception e) {
            LogLevelSave(1,"Personel listeleme işlemi başarısız oldu");
            return new ErrorResult("Personel listeleme işlemi başarısız oldu",false);
        }
    }
    @Override
    public Result getById(Long id){
        try{
            StaffDTO staffDTO = entityToDto(staffDao.getById(id),StaffMapper::toDTO);
            LogLevelSave(2,"İd değerine göre personel listelendi");
            return new SuccessDataResult("İd değerine ait personel listelendi",true,staffDTO);
        } catch (Exception e) {
            LogLevelSave(1,"Girilen id değerine ait bir personel bulunamadı");
            return new ErrorResult("Girilen id değerine ait bir personel bulunamadı",false);
        }
    }
    @Override
    public Result addPersonnelRequest(PersonelRequestFormDTO personelRequestFormDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) { // CustomUserDetails, UserDetails'in bir özelleştirilmiş hali olmalı
            String username = ((UserDetails) principal).getUsername(); // Username değerini al

            User user = userDao.findUserByUsername(username);
            Manager manager = managerDao.findByMail(user.getMail());

            personelRequestFormDTO.setRequestorManagerId(manager.getId());
            PersonnelRequestForm personnelRequestForm = dtoToEntity(personelRequestFormDTO, PersonelRequestFormMapper::toEntity);

            personelRequestFormDao.save(personnelRequestForm);
        }
        return new SuccessDataResult("Personel talep isteği gönderildi",true,personelRequestFormDTO);
    }
    @Override
    public Result saveStaff(StaffDTO staffDTO,String password){
        try{
            List<Staff> staffs = staffDao.findAll();
            if (uniqueControl(staffs,staffDTO,"getPhoneNumber") ||  uniqueControl(staffs,staffDTO,"getMail")){
                LogLevelSave(1,"Mail veya telefon nummarası benzersiz olmalıdır");
                return new ErrorResult("Mail veya telefon numarası benzersiz olmaıl",false);
            }
            staffDao.save(dtoToEntity(staffDTO,StaffMapper::toEntity));
            userService.saveDormitoryUser(staffDTO,"ROLE_STAFF",password,staffDTO.getName(), staffDTO.getSurName());
            LogLevelSave(3,"Personel kaydetme işlemi başarılır");
            return new SuccessDataResult("Personel ekleme işlemi başarılı",true,staffDTO);
        } catch (Exception e) {
            LogLevelSave(1, "Personel ekleme işlemi başarısız");
            return new ErrorResult("Personel ekleme işlemi başarısız",false);
        }
    }

    @Override
    public Result updateStaff(Long id, StaffDTO staffDTO){
        Map<String,String> updateUser = new HashMap<>();
        try {
            Staff editStaff = staffDao.getById(id);
            List<Staff> staffs = staffDao.findAll();
            staffs.remove(editStaff);

            if (uniqueControl(staffs,staffDTO,"getPhoneNumber") ||  uniqueControl(staffs,staffDTO,"getMail")){
                LogLevelSave(1,"Mail veya telefon nummarası benzersiz olmalıdır");
                return new ErrorResult("Mail veya telefon numarası benzersiz olmaıl",false);
            }

            if (editStaff.getDepartment().getIsDeleted() || editStaff.getTitle().getIsDeleted() || editStaff.getManager().getIsDeleted()){
                LogLevelSave(1,"Personel güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
                return new ErrorResult("Personel güncelleme işleminde, ilişkili olacağı tablo kaldırılmış",false);
            }

            updateUser.put("name",staffDTO.getName());
            updateUser.put("surName",staffDTO.getSurName());
            updateUser.put("mail",staffDTO.getMail());
            userService.updateDormitoryUser(updateUser);

            Staff staff = dtoToEntity(staffDTO,StaffMapper::toEntity);
            staff.setId(editStaff.getId());
            editStaff = staff;
            staffDao.save(editStaff);
            LogLevelSave(3,"Personel güncelleme işlemi başarılı.");
            return new SuccessDataResult("Personel güncelleme işlemi başarılı",true,entityToDto(editStaff,StaffMapper::toDTO));
        }
        catch (Exception e) {
            LogLevelSave(1, "Bu id değerine ait bir personel bulunamadı.");
            return new ErrorResult("Bu id değerinde personel bulunamadı",false);
        }
    }
    @Override
    public Result deleteStaff(Long id){
        Staff deleteStaff;
        try {
            deleteStaff = staffDao.getById(id);
            userService.deleteDormitoryUser(deleteStaff.getMail());
            LogLevelSave(3,"Personel silme İşlemi başarılı.");
            deleteStaff.setDeleted(true);
            staffDao.save(deleteStaff);
            return new SuccesResult("Personel silme işlemi başarılı",true);
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir personel bulunamadı.");
            return new ErrorResult("Bu id değerinde personel bulunamadı",false);
        }
    }
}
