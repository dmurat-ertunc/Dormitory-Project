package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IRentalService;
import com.dme.DormitoryProject.business.services.IStudentRequestRentalService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalMapper;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaMapper;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RentalManager extends BaseClass implements IRentalService {

    private IRentalDao rentalDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IStudentDao studentDao;
    private ISportAreaDao sportAreaDao;
    private IStudentRequestRentalService studentRequestRentalService;
    private IStudentRequestRentalDao studentRequestRentalDao;

    @Autowired
    public RentalManager(IRentalDao rentalDao, ILgoDao lgoDao, ILogLevelDao logLevelDao,
                         IStudentDao studentDao, ISportAreaDao sportAreaDao,
                         IStudentRequestRentalService studentRequestRentalService,
                         IStudentRequestRentalDao studentRequestRentalDao) {
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.studentDao = studentDao;
        this.sportAreaDao = sportAreaDao;
        this.studentRequestRentalService = studentRequestRentalService;
        this.studentRequestRentalDao = studentRequestRentalDao;
    }

    private LocalDateTime localDateTime(){
        LocalDateTime currentLocalTime = LocalDateTime.now();
        return currentLocalTime;
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
        try{
            List<RentalDTO> rentalDTOS = entityToDtoList(rentalDao.findAll(),RentalMapper::toDTO);
            LogLevelSave(2,"Kiralamalar listelendi");
            return new SuccessDataResult("Tüm kiralamalar listelendi",true,rentalDTOS);
        } catch (Exception e) {
            LogLevelSave(1,"Kiralamalar listenirken bir hata oluştu.");
            return new ErrorResult("Kiralamalar listelenirken bir hata oluştu",false);
        }
    }
    @Override
    public Result getById(Long id){
        try {
            RentalDTO rentalDTO= entityToDto(rentalDao.getById(id),RentalMapper::toDTO);
            LogLevelSave(2,"İd değerine göre kiralama listelendi");
            return new SuccessDataResult("İd değerine göre kiralama listelendi",true,rentalDTO);
        } catch (Exception e) {
            LogLevelSave(1,"İd değerine gmre kiralama listelenmesinde hata oluştu");
            return new ErrorResult("İd değerine gmre kiralama listelenmesinde hata oluştu",false);
        }
    }
    @Override
    public Result afterRental(LocalTime startTime){
        List<Rental> rentals = rentalDao.findByStartTimeAfter(startTime);
        if (rentals !=  null && !rentals.isEmpty()){
            LogLevelSave(3, "Girilen saatten sonra olan kiralamalar k-listelendi");
            return new SuccessDataResult("Girilen saatten sonra olan kiralamalar k-listelendi",true,entityToDtoList(rentals,RentalMapper::toDTO));
        }
        LogLevelSave(1,"Kiralama bulunamadı");
        return new ErrorResult("Kiralama bulunamadı",false);
    }

    @Override
    public Result addRentalRequest(StudentRequestRentalDTO studentRequestRentalDTO){
        List<SportArea> sportAreaList = new ArrayList<>();
        List<SportArea> sportAreaList2 = new ArrayList<>();
        sportAreaList = rentalDao.findOverlappingRentals(studentRequestRentalDTO.getStartTime(),studentRequestRentalDTO.getEndTime(),studentRequestRentalDTO.getRentalDate());
        for (SportArea sportArea : sportAreaList){
            if (sportArea.getId() == studentRequestRentalDTO.getSportAreaId()){
                return new  ErrorResult("Bu saha bu saatler içinde dolu",false);
            }
        }
        sportAreaList2 = studentRequestRentalDao.findOverlappingRentals(studentRequestRentalDTO.getStartTime(),studentRequestRentalDTO.getEndTime(),studentRequestRentalDTO.getRentalDate());
        for (SportArea sportArea : sportAreaList){
            if (sportArea.getId() == studentRequestRentalDTO.getSportAreaId()){
                return new  ErrorResult("Bu sahaya bu saatler için zaten istek atılmış, başka bir saat aralığı belirleyin",false);
            }
        }
        studentRequestRentalService.addRequest(studentRequestRentalDTO);
        return new SuccesResult("Kiralama isteğiniz gönderildi, onaylanması halinde mail olarak size bildirilecektir",true);
    }
    @Override
    public Result updateRental(Long id, RentalDTO rentalDTO){
        try{
            Rental editRental = rentalDao.getById(id);
            Rental rental = dtoToEntity(rentalDTO,RentalMapper::toEntity);
            if (rental.getSportArea().getIsDeleted() || rental.getStudent().isDeleted()){
                LogLevelSave(1,"Kiralama güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
                return new ErrorResult("Kiralama güncelleme işleminde, ilişki olacağı tablo kaldırılmış.",false);
            }
            List<Rental> rentals = rentalDao.findAll();
            rentals.remove(editRental);
            for(Rental rental1 : rentals){
                if ((rentalDTO.getStartTime().isAfter(rental1.getStartTime()) || Objects.equals(rentalDTO.getStartTime(),rental1.getStartTime()))
                        && rentalDTO.getStartTime().isBefore(rental1.getEndTime())
                        && Objects.equals(rentalDTO.getRentalDate(),rental1.getRentalDate())
                        && Objects.equals(rental1.getSportArea().getId(),rentalDTO.getSportAreaId())) {
                    LogLevelSave(1, "Bu alan daha önce kiralanmış");
                    return new ErrorResult("Bu alan daha önce kiralanmış",false);
                }
            }
            editRental.setSportArea(sportAreaDao.getById(rentalDTO.getSportAreaId()));
            editRental.setStudent(studentDao.getById(rentalDTO.getStudentId()));
            editRental.setEndTime(rental.getEndTime());
            editRental.setRentalDate(rental.getRentalDate());
            editRental.setStartTime(rental.getStartTime());
            rentalDao.save(editRental);
            LogLevelSave(3,"Kiralama güncelleme işlemi başarılır");
            return new SuccessDataResult("Kiralama güncelleme işlemi başarılı",true,rentalDTO);
        } catch (Exception e) {
            LogLevelSave(1, "Kiralama güncelleme işlemi başarısız");
            return new ErrorResult("Kiralama güncelleme işlemi başarısız",false);
        }

    }
    @Override
    public Result deleteRental(Long id){
        try {
            Rental rental = rentalDao.getById(id);
            rental.setDeleted(true);
            LogLevelSave(3,"Kiralama silme işlemi başarılı");
            return new SuccesResult("Kiralama silme işlemi başarılı",true);
        } catch (Exception e) {
            LogLevelSave(1,"Silme işlemi başarısız");
            return new ErrorResult("Silme işlemi başarısızı",false);
        }
    }

    @Override
    public Result emptyField(LocalTime startTime, LocalTime endTime, LocalDate date){
        List<SportAreaDTO> sportAreaDTOS = entityToDtoList(rentalDao.findOverlappingRentals(startTime,endTime,date),SportAreaMapper::toDTO);
        return new SuccessDataResult("Girilen saatler arasında boş olan sahalar",true,sportAreaDTOS);
    }
}
//"startTime": "14:30:00",
//    "endTime": "18:45:30"
