package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IRoomService;
import com.dme.DormitoryProject.dtos.room.RoomDto;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    private IRoomService roomService;

    @Autowired
    public RoomController(IRoomService roomService){
        this.roomService=roomService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.roomService.getAll();
    }

    @GetMapping("emptyRoom")
    public Result emptyRoom(){
        return this.roomService.emptyRoom();
    }

    @GetMapping("getByRoomNo")
    public Result getByRoomNo(@RequestParam Long roomNo){
        return this.roomService.getByRoomNo(roomNo);
    }

    @PostMapping("save")
    public Result save(@Valid @RequestBody RoomDto roomDto){
        return this.roomService.save(roomDto);
    }

    @PutMapping("addStudentsToRoom")
    public Result addStudentsToRoom(@RequestParam Long studentId, @RequestParam Long roomNo){
        return this.roomService.addStudentsToRoom(studentId,roomNo);
    }

    @PutMapping("studentsRoomChange")
    public Result studentsRoomChange(@RequestParam Long studentId, @RequestParam Long roomNo){
        return this.roomService.studentsRoomChange(studentId,roomNo);
    }


}
