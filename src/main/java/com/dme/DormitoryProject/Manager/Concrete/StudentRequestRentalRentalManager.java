package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IMailService;
import com.dme.DormitoryProject.Manager.Abstract.IRentalService;
import com.dme.DormitoryProject.Manager.Abstract.IStudentRequestRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestMapper;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import com.dme.DormitoryProject.enums.RequestStatus;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRequestRentalRentalManager implements IStudentRequestRentalService {
    private IStudentRequestRentalDao studentRequestRentalDao;
    private IStudentDao studentDao;
    private ISportAreaDao sportAreaDao;
    private IRentalDao rentalDao;
    private IMailService mailService;


    @Autowired
    public StudentRequestRentalRentalManager(IStudentRequestRentalDao studentRequestRentalDao, ISportAreaDao sportAreaDao,
                                             IStudentDao studentDao, IRentalDao rentalDao, IMailService mailService){
        this.studentRequestRentalDao=studentRequestRentalDao;
        this.studentDao=studentDao;
        this.sportAreaDao=sportAreaDao;
        this.rentalDao=rentalDao;
        this.mailService=mailService;
    }

    private StudentRequestRental dtoToEntity(StudentRequestRentalDTO studentRequestRentalDTO){
        return StudentRequestMapper.toEntity(studentRequestRentalDTO,studentDao,sportAreaDao);
    }

    @Override
    public void addRequest(StudentRequestRentalDTO studentRequestRentalDTO) {
        studentRequestRentalDao.save(dtoToEntity(studentRequestRentalDTO));
    }
    @Override
    public Result permitRequest(Long id){
        StudentRequestRental studentRequestRental = studentRequestRentalDao.getById(id);
        studentRequestRental.setStatus(RequestStatus.Approved);
        studentRequestRentalDao.save(studentRequestRental);
        if (saveRental(studentRequestRental)){
            return new Result<>("İstek onaylandı, onay maili gönderildi",true);
        }
        return new ErrorResult("Onaylama işleminde hata oluştu",false);
    }

    private boolean saveRental(StudentRequestRental studentRequestRental){
        try {
            Rental rental = new Rental();
            rental.setEndTime(studentRequestRental.getEndTime());
            rental.setRentalDate(studentRequestRental.getRentalDate());
            rental.setStartTime(studentRequestRental.getStartTime());
            rental.setStudent(rental.getStudent());
            rental.setSportArea(studentRequestRental.getSportArea());
            rentalDao.save(rental);
            mailService.permitMailSending(studentRequestRental.getStudent().getMail(),studentRequestRental.getSportArea().getSporType()
                    ,studentRequestRental.getStartTime(),studentRequestRental.getEndTime());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
