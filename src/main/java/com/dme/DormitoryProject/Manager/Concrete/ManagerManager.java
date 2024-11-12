package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IManagerService;
import com.dme.DormitoryProject.Manager.Abstract.IUserService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.exception.GlobalExceptionHandler;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IManagerDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerManager implements IManagerService {
    private IManagerDao managerDao;
    private ILgoDao logDao;
    private ILogLevelDao logLevelDao;
    private IStaffDao staffDao;
    private GlobalExceptionHandler globalExceptionHandler;
    private IUserService userService;
    @Autowired
    public ManagerManager(IManagerDao managerDao, ILgoDao logDao,ILogLevelDao logLevelDao,IStaffDao staffDao,
                          GlobalExceptionHandler globalExceptionHandler,IUserService userService){
        this.managerDao=managerDao;
        this.logDao=logDao;
        this.logLevelDao=logLevelDao;
        this.staffDao=staffDao;
        this.globalExceptionHandler=globalExceptionHandler;
        this.userService=userService;
    }
    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        logDao.save(log);
    }
    public List<ManagerDTO> entityToDtoList(List<Manager> managers){
        List<ManagerDTO> managerDTOS = new ArrayList<>();
        for (Manager manager : managers) {
            ManagerDTO dto = ManagerMapper.toDTO(manager);
            managerDTOS.add(dto);
        }
        return managerDTOS;
    }
    public ManagerDTO entityToDtoObject(Manager manager){
        return ManagerMapper.toDTO(manager);
    }
    public Manager dtoToEntity(ManagerDTO managerDTO){
        return ManagerMapper.toEntitiy(managerDTO);
    }
    @Override
    public Result getAll(){
        try {
            List<Manager> managerList = managerDao.findAll();
            List<ManagerDTO> managerDTOList = entityToDtoList(managerList);
            return new SuccessDataResult("Tüm yöneticileri listeleme işlemi başarılı",true,managerDTOList);
        } catch (Exception e) {
            return new ErrorResult("Tüm yöneticileri listeleme işlemi başarısız",false);
        }
    }
    @Override
    public Result getById(Long id){
        try {
            Manager findManager = managerDao.getById(id);
            LogLevelSave(3,"İd değerine göre yönetici işlemi başarılı");
            return new SuccessDataResult("İd değerine göre yönetici verisi, başarılı bir şekilde döndürüldü",true, entityToDtoObject(findManager));
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir yönetici bulunamadı.");
            return new ErrorResult("Bu id değerine ait yönetici bulunamadı",false);
        }
    }
    @Override
    public Result findBySalaryGreaterThan(int salary){
        List<Manager> managers = managerDao.findBySalaryGreaterThan(salary);
        if (managers !=  null && !managers.isEmpty()){
            LogLevelSave(3, "Belirtilen miktardan fazla maaş alan yöneticileri başarılı şekilde listelendi");
            return new SuccessDataResult("Belirtilen miktardan fazla maaş alan yöneticileri başarılı şekilde listelendi",true,entityToDtoList(managers));
        }
        LogLevelSave(1,"Belirtilen miktardan fazla maaş alan yönetici bulunamadı");
        return new ErrorResult("Belirtilen miktardan fazla maaş alan yönetici bulunamadı",false);
    }
    @Override
    public Result saveManager(ManagerDTO managerDTO, String passwword){
        try {
            List<Manager> managers = managerDao.findAll();
            if (control(managers,managerDTO,"getMail") || control(managers,managerDTO,"getPhoneNumber")){
                LogLevelSave(1,"Mail veya telefon nummarası benzersiz olmalıdır");
                return new ErrorResult("Mail veya telefon numarası benzersiz olmaıl",false);
            }
            if (managerDTO.getTitle().contains("Genel")){
                userService.saveDormitoryUser(managerDTO,"ROLE_MANAGERADMIN",passwword,managerDTO.getName(), managerDTO.getSurName());
            }else {
                userService.saveDormitoryUser(managerDTO,"ROLE_MANAGER",passwword, managerDTO.getName(),managerDTO.getSurName());
            }
            managerDao.save(dtoToEntity(managerDTO));
            LogLevelSave(3,"Yönetici ekleme işlemi başarılı");
            return new SuccessDataResult("Yönetici ekleme işlemi başarılı",true,managerDTO);
        }catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Yönetici ekleme işlemi başarısız");
            return new ErrorResult("Yönetici ekleme işlemi başarısız",false);
        }
    }
    public Result updateManager(Long id, ManagerDTO managerDTO){
        Map<String,String> updateUser = new HashMap<>();
        try {
            Manager editManager = managerDao.getById(id);
            List<Manager> managers = managerDao.findAll();

            managers.remove(editManager);
            if (control(managers,managerDTO,"getPhoneNumber") ||  control(managers,managerDTO,"getMail")){
                LogLevelSave(1,"Mail veya telefon nummarası benzersiz olmalıdır");
                return new ErrorResult("Mail veya telefon numarası benzersiz olmaıl",false);
            }
            updateUser.put("mail",managerDTO.getMail());
            updateUser.put("name",managerDTO.getName());
            updateUser.put("surName",managerDTO.getSurName());
            userService.updateDormitoryUser(updateUser);

            editManager = managerDao.getById(id);
            editManager.setName(managerDTO.getName());
            editManager.setMail(managerDTO.getMail());
            editManager.setPhoneNumber(managerDTO.getPhoneNumber());
            editManager.setSalary(managerDTO.getSalary());
            editManager.setSurName(managerDTO.getSurName());
            editManager.setTitle(managerDTO.getTitle());
            LogLevelSave(3,"Yöentici güncelleme işlemi başarılı");
            managerDao.save(editManager);
            return new SuccessDataResult("Yönetici güncelleme  işlemi başarılı",true,entityToDtoObject(editManager));
        }
        catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir yönetici bulunamadı.");
            return new ErrorResult("Bu id değerinde yönetici bulunamadı",false);
        }
    }
    public Result deleteManager(Long id){
        Manager deleteManager;
        try {
            deleteManager = managerDao.getById(id);
            List<Staff> staffList = staffDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
            for (Staff staff : staffList) {
                if (staff.getManager().getId() == id) {
                    LogLevelSave(4, "Bu manager yönetici ile ilişkili, siliniemez.");
                    return new ErrorResult("Bu yönetici, çalışan ile ilişkil, siliniemez.",false);
                }
            }
            userService.deleteDormitoryUser(deleteManager.getMail());
            LogLevelSave(3,"Yönetici silme İşlemi başarılı.");
            deleteManager.setDeleted(true);
            managerDao.save(deleteManager);
            return new SuccesResult("Yönetici silme işlemi başarılı",true);
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir yönetici bulunamadı.");
            return new ErrorResult("Bu id değerinde yönetici bulunamadı",false);
        }
    }
    public boolean control(List<Manager> managers, ManagerDTO managerDTO, String metot){
        try {
            // StudentDTO nesnesindeki ilgili metodu çağırarak değeri al
            Method dtoMethod = managerDTO.getClass().getMethod(metot);
            Object dtoValue = dtoMethod.invoke(managerDTO);
            for (Manager manager : managers) {
                Method studentMethod = manager.getClass().getMethod(metot);
                Object studentValue = studentMethod.invoke(manager);
                if (studentValue.equals(dtoValue)) {
                    LogLevelSave(1,"Aynı " + metot + "değerine sahip yönetici bulundu");
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Belirtilen metot bulunamadı: " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
