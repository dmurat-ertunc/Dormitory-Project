package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IPunishmentService;
import com.dme.DormitoryProject.dtos.punishment.PunishmentDTO;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/punishment")
public class PunishmentController {

    private IPunishmentService punishmentService;

    @Autowired
    public PunishmentController(IPunishmentService punishmentService){
        this.punishmentService=punishmentService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.punishmentService.getAll();
    }

    @PostMapping("save")
    public Result save(@Valid @RequestBody PunishmentDTO punishmentDTO){
        return this.punishmentService.save(punishmentDTO);
    }

}
