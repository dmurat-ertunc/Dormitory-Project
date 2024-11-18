package com.dme.DormitoryProject.controller;

import com.dme.DormitoryProject.business.services.IPersonnelRequestFormService;
import com.dme.DormitoryProject.business.services.IStaffService;
import com.dme.DormitoryProject.dtos.personelRequestFormDtos.PersonelRequestFormDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/staffs/")
public class StaffController {
    private IStaffService staffService;
    private IPersonnelRequestFormService personnelRequestFormService;
    @Autowired
    public StaffController(IStaffService staffService, IPersonnelRequestFormService personnelRequestFormService){
        this.staffService=staffService;
        this.personnelRequestFormService=personnelRequestFormService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.staffService.getAll();
    }
    @GetMapping("staffId/{id}")
    public Result getById(@PathVariable Long id){
        return this.staffService.getById(id);
    }
    @PostMapping("saveStaff")
    public Result saveStaff(@RequestBody @Valid StaffDTO staffDTO,@RequestParam String password){
        return this.staffService.saveStaff(staffDTO,password);
    }
    @PostMapping("addPersonnelRequest")
    public Result addPersonnelRequest(@RequestBody @Valid PersonelRequestFormDTO personelRequestFormDTO){
        return this.staffService.addPersonnelRequest(personelRequestFormDTO);
    }
    @PutMapping("update/{id}")
    public Result updateStaff(@PathVariable Long id,@RequestBody @Valid StaffDTO staffDTO){
        return this.staffService.updateStaff(id,staffDTO);
    }
    @PutMapping("delete/{id}")
    public  Result deleteStaff(@PathVariable Long id){
        return this.staffService.deleteStaff(id);
    }
}
