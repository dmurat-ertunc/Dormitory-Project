package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IEntryExitService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/entryExit")
public class EntryExitController {

    private IEntryExitService entryExitService;

    @Autowired
    public EntryExitController(IEntryExitService entryExitService){
        this.entryExitService=entryExitService;
    }

    @GetMapping("/getAll")
    public Result getAll(){
        return this.entryExitService.getAll();
    }

    @GetMapping("entry/{id}")
    public Result entry(@PathVariable Long id){
        return this.entryExitService.entry(id);
    }
    @GetMapping("exit/{id}")
    public Result exit(@PathVariable Long id){
        return this.entryExitService.exit(id);
    }
}
