package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IPunishmentDefinitionsService;
import com.dme.DormitoryProject.dtos.punishmentDefinitions.PunishmentDefinitionsDTO;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/punishmentDefinitions")
public class PunishmentDefinitionsController {

    private IPunishmentDefinitionsService punishmentDefinitionsService;

    @Autowired
    public PunishmentDefinitionsController(IPunishmentDefinitionsService punishmentDefinitionsService){
        this.punishmentDefinitionsService=punishmentDefinitionsService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.punishmentDefinitionsService.getAll();
    }

    @PostMapping("save")
    public Result save(@Valid @RequestBody PunishmentDefinitionsDTO punishmentDefinitionsDTO){
        return this.punishmentDefinitionsService.save(punishmentDefinitionsDTO);
    }

    @PutMapping("update/{id}")
    public Result update(@Valid @RequestBody PunishmentDefinitionsDTO punishmentDefinitionsDTO,@PathVariable Long id){
        return this.punishmentDefinitionsService.update(punishmentDefinitionsDTO,id);
    }
}
