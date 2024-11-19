package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IPersonnelRequestFormService;
import com.dme.DormitoryProject.business.services.IStudentRequestRentalService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/requests")
public class RequestController {

    private IStudentRequestRentalService studentRequestRentalService;
    private IPersonnelRequestFormService personnelRequestFormService;

    @Autowired
    public RequestController(IStudentRequestRentalService studentRequestRentalService,
                             IPersonnelRequestFormService personnelRequestFormService){
        this.studentRequestRentalService=studentRequestRentalService;
        this.personnelRequestFormService=personnelRequestFormService;
    }

    @PutMapping("permitRentalRequest/{id}")
    public Result permitRentalRequest(@PathVariable Long id){
        return this.studentRequestRentalService.permitRentalRequest(id);
    }
    @PutMapping("rejectedRentalRequest/{id}")
    public Result rejectedRentalRequest(@PathVariable Long id){
        return this.studentRequestRentalService.rejectedRentalRequest(id);
    }
    @GetMapping("getAllPersonnelRequest")
    public Result getAllPersonnelRequest(){
        return this.personnelRequestFormService.getAllPersonnelRequest();
    }
    @PutMapping("acceptPersonnelRequest/{id}")
    public Result acceptPersonnelRequest(@PathVariable Long id){
        return this.personnelRequestFormService.acceptPersonnelRequest(id);
    }

}
