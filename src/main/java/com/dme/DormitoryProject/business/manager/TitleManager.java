package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.ITitleService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.dtos.titleDtos.TitleMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.repository.ITitleDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleManager extends BaseClass implements ITitleService {
    private ITitleDao titleDao;
    private IStaffDao staffDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;


    @Autowired
    public TitleManager(ITitleDao titleDao, IStaffDao staffDao, ILgoDao lgoDao, ILogLevelDao logLevelDao) {
        this.titleDao = titleDao;
        this.staffDao = staffDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        log.setDeleted(false);
        lgoDao.save(log);
    }

    @Override
    public Result getAll(){
        try{
            List<TitleDTO> titlesDto = entityToDtoList(titleDao.findAll(),TitleMapper::toDTO);
            LogLevelSave(3,"Tüm ünvanlar listelendi");
            return new SuccessDataResult("Tüm ünvanlar listelendi",true,titlesDto);
        }catch (Exception e){
            return new ErrorResult("Ünvanların listeleme işleminde hata oluştu",false);
        }
    }
    @Override
    public Result getById(Long id) {
        try {
            Title title = titleDao.getById(id);
            LogLevelSave(3,"İd değerine göre ünvan listelendi");
            return new SuccessDataResult("İd değerine göre ünvan listelendi",true,entityToDto(title,TitleMapper::toDTO));
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine ait bir ünvan bulunamadı.");
            return new ErrorResult("Bu id değerine ait ünvan bulunamadı",false);
        }
    }
    @Override
    public Result saveTitle(TitleDTO titleDTO){
        try {
            titleDao.save(dtoToEntity(titleDTO,TitleMapper::toEntity));
            LogLevelSave(3,"Ünvan ekleme işlemi başarılı");
            return new SuccessDataResult("Ünvan ekleme işlemi başarılı",true,titleDTO);
        } catch (Exception e) {
            LogLevelSave(1,"Ünvan ekleme işlemi başarısız");
            return new ErrorResult("Ünvan ekleme işlemi başarısız",false);
        }

    }
    @Override
    public Result updateTitle(Long id, TitleDTO titleDTO) {
        Title editTitle;
        try {
            editTitle = titleDao.getById(id);
            editTitle.setName(titleDTO.getName());
            titleDao.save(editTitle);
            LogLevelSave(3,"Ünvan güncelleme işlemi başarılı");
            return new SuccessDataResult("Ünvan güncelleme  işlemi başarılı",true,entityToDto(editTitle,TitleMapper::toDTO));
        }catch (Exception e){
            LogLevelSave(1,"Bu id değerine ait bir ünvan bulunamadı");
            return new ErrorResult("Bu id değerine ait bir ünvan bulunamadı",false);
        }
    }
    @Override
    public Result deleteTitle(Long id){
        Title deleteTitle;
        try {
            deleteTitle = titleDao.getById(id);
            List<Staff> staffList = staffDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
            for (Staff staff : staffList) {
                if (staff.getTitle().getId() == id) {
                    LogLevelSave(4, "Bu ünvan, çalışan ile ilişkili, siliniemez.");
                    return new ErrorResult("Bu ünvan, çalışan ile ilişkil, siliniemez.",false);
                }
            }
            LogLevelSave(3,"Ünvan silme İşlemi başarılı.");
            deleteTitle.setDeleted(true);
            titleDao.save(deleteTitle);
            return new SuccesResult("Ünvan silme işlemi başarılı",true);
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir ünvan bulunamadı.");
            return new ErrorResult("Bu id değerinde ünvan bulunamadı",false);
        }
    }
}
