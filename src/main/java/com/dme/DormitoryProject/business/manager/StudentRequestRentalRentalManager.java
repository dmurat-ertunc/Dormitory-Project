package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IStudentRequestRentalService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestMapper;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.entity.Mail;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import com.dme.DormitoryProject.enums.RequestStatus;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRequestRentalRentalManager extends BaseClass implements IStudentRequestRentalService {
    private IStudentRequestRentalDao studentRequestRentalDao;
    private IStudentDao studentDao;
    private ISportAreaDao sportAreaDao;
    private IRentalDao rentalDao;



    @Autowired
    public StudentRequestRentalRentalManager(IStudentRequestRentalDao studentRequestRentalDao, ISportAreaDao sportAreaDao,
                                             IStudentDao studentDao, IRentalDao rentalDao){
        this.studentRequestRentalDao=studentRequestRentalDao;
        this.studentDao=studentDao;
        this.sportAreaDao=sportAreaDao;
        this.rentalDao=rentalDao;

    }


    @Override
    public void addRequest(StudentRequestRentalDTO studentRequestRentalDTO) {
        studentRequestRentalDao.save(dtoToEntity(studentRequestRentalDTO,StudentRequestMapper::toEntity));
    }
    @Override
    public Result permitRentalRequest(Long id){
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
            Mail mail = new Mail();

            rental.setEndTime(studentRequestRental.getEndTime());
            rental.setRentalDate(studentRequestRental.getRentalDate());
            rental.setStartTime(studentRequestRental.getStartTime());
            rental.setStudent(studentRequestRental.getStudent());
            rental.setSportArea(studentRequestRental.getSportArea());
            rentalDao.save(rental);

            mail.setFromMail("cengdme@gmail.com");
            mail.setToMail(studentRequestRental.getStudent().getMail());
            mail.setSubject("İstek Cevabı");
            mail.setText(String.valueOf(studentRequestRental.getSportArea().getSporType() + " alanı "
                    + studentRequestRental.getRentalDate() + " tarihinde "
                    + studentRequestRental.getStartTime() + " ile " + studentRequestRental.getEndTime() + "" +
                    "arasında isteğiniz doğrultusunda onaylanmıştır"));
            sendMail(mail);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Result rejectedRentalRequest(Long id){
        try {
            StudentRequestRental studentRequestRental = studentRequestRentalDao.getById(id);
            Mail mail = new Mail();

            studentRequestRental.setStatus(RequestStatus.Rejected);
            studentRequestRentalDao.save(studentRequestRental);

            mail.setFromMail("cengdme@gmail.com");
            mail.setToMail(studentRequestRental.getStudent().getMail());
            mail.setSubject("İstek Cevabı");
            mail.setText(String.valueOf(studentRequestRental.getSportArea().getSporType() + " alanı "
                    + studentRequestRental.getRentalDate() + " tarihinde "
                    + studentRequestRental.getStartTime() + " ile " + studentRequestRental.getEndTime() + "" +
                    "arasında isteğiniz doğrultusunda reddedilmiştir"));
            sendMail(mail);
            return new SuccesResult("İstek reddedildi, red maili gönderildi",true);
        } catch (Exception e) {
            return new ErrorResult("Hata oluştu",false);
        }
    }
}
