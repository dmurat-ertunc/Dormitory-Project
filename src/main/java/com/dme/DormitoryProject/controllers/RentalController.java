package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("api/rentals/")
public class RentalController {

    private IRentalService rentalService;
    @Autowired
    public RentalController(IRentalService rentalService){
        this.rentalService=rentalService;
    }
    @GetMapping("getAll")
    public Result getAll(){
        return this.rentalService.getAll();
    }
    @GetMapping("rentalId/{id}")
    public Result getById(@PathVariable Long id){
        return this.rentalService.getById(id);
    }
    @GetMapping("afterStartTime")
    public Result afterStartTime(@RequestParam LocalTime startTime){
        return this.rentalService.afterRental(startTime);
    }
    @PostMapping("addRentalRequest")
    public Result addRentalRequest(@RequestBody StudentRequestRentalDTO studentRequestRentalDTO){
        return this.rentalService.addRentalRequest(studentRequestRentalDTO);
    }
    @PutMapping("update/{id}")
    public Result updateRental(@PathVariable Long id,@RequestBody RentalDTO rentalDTO){
        return this.rentalService.updateRental(id,rentalDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteRental(@PathVariable Long id){
        return this.rentalService.deleteRental(id);
    }
    @GetMapping("emptyField")
    public Result emptyFiled(@RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam LocalDate date){
        return  this.rentalService.emptyField(startTime,endTime,date);
    }
}
