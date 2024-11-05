package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStudentRequestRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestMapper;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import com.dme.DormitoryProject.enums.RequestStatus;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRequestRentalRentalManager implements IStudentRequestRentalService {
    private IStudentRequestRentalDao studentRequestRentalDao;
    private IStudentDao studentDao;
    private ISportAreaDao sportAreaDao;

    @Autowired
    public StudentRequestRentalRentalManager(IStudentRequestRentalDao studentRequestRentalDao, ISportAreaDao sportAreaDao, IStudentDao studentDao){
        this.studentRequestRentalDao=studentRequestRentalDao;
        this.studentDao=studentDao;
        this.sportAreaDao=sportAreaDao;
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
        return new Result<>("İstek onaylandı",true);
    }
}
