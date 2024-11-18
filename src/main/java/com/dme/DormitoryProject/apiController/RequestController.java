package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IStudentRequestRentalService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/requests")
public class RequestController {

    private IStudentRequestRentalService studentRequestRentalService;

    @Autowired
    public RequestController(IStudentRequestRentalService studentRequestRentalService){
        this.studentRequestRentalService=studentRequestRentalService;
    }

    @PutMapping("permitRentalRequest/{id}")
    public Result permitRentalRequest(@PathVariable Long id){
        return this.studentRequestRentalService.permitRentalRequest(id);
    }
    @PutMapping("rejectedRentalRequest/{id}")
    public Result rejectedRentalRequest(@PathVariable Long id){
        return this.studentRequestRentalService.rejectedRentalRequest(id);
    }

}