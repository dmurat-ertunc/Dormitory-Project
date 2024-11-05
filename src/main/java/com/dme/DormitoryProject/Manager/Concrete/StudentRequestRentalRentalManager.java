package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStudentRequestRentalService;
import com.dme.DormitoryProject.repository.IStudentRequestRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRequestRentalRentalManager implements IStudentRequestRentalService {
    private IStudentRequestRentalDao studentRequestRentalDao;

    @Autowired
    public StudentRequestRentalRentalManager(IStudentRequestRentalDao studentRequestRentalDao){
        this.studentRequestRentalDao=studentRequestRentalDao;
    }
}
