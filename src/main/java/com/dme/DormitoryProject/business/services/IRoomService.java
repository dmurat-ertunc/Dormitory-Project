package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.room.RoomDto;
import com.dme.DormitoryProject.response.Result;

public interface IRoomService {
    Result getAll();
    Result emptyRoom();
    Result getByRoomNo(Long roomNo);
    Result save(RoomDto roomDto);
    Result addStudentsToRoom(Long studentId, Long roomNo);
    Result studentsRoomChange(Long studentId, Long roomNo);
}
