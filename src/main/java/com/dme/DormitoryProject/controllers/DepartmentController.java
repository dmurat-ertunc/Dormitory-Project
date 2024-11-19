package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IDepartmentService;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments/")
public class DepartmentController {

    private IDepartmentService departmentService;

    @Autowired
    public  DepartmentController(IDepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.departmentService.getAll();
    }

    @GetMapping("departmentId/{id}")
    public Result getById(@PathVariable Long id){
        return this.departmentService.getById(id);
    }

    @PostMapping("saveDepartment")
    public Result saveDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        return this.departmentService.saveDepartment(departmentDTO);
    }
    @PutMapping("update/{id}")
    public Result updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDTO departmentDTO ){
        return this.departmentService.updateDepartment(id,departmentDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteDepartment(@PathVariable Long id){
        return this.departmentService.deleteDepartment(id);
    }
    @GetMapping("startingWithWord")
    public Result startingWithWord(@RequestParam String prefix){
        return this.departmentService.startingWithWord(prefix);
    }


}
