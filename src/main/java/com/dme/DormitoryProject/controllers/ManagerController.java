package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IManagerService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/managers")
public class ManagerController {

    private IManagerService managerService;

    @Autowired
    public ManagerController(IManagerService managerService){
        this.managerService=managerService;
    }

    @GetMapping("getAll")
    public Result getAll() {
        return this.managerService.getAll();
    }
    @GetMapping("managerId/{id}")
    public Result getById(@PathVariable Long id){
        return this.managerService.getById(id);
    }
    @GetMapping("findSalaryGreater")
    public Result findSalaryGreater(@RequestParam int salary){
        return this.managerService.findBySalaryGreaterThan(salary);
    }
    @PostMapping("saveManager")
    public Result saveManager(@RequestBody @Valid ManagerDTO managerDTO, @RequestParam String password){
        return this.managerService.saveManager(managerDTO,password);
    }
    @PutMapping("update/{id}")
    public Result updateManager(@PathVariable Long id,@RequestBody @Valid ManagerDTO managerDTO){
        return managerService.updateManager(id,managerDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteManager(@PathVariable Long id){
        return this.managerService.deleteManager(id);
    }
}
