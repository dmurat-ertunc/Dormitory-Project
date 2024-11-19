package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IPunishmentService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
