package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.room.RoomDto;
import com.dme.DormitoryProject.response.Result;

public interface IRoomService {
    Result getAll();
    Result getByRoomNo(Long roomNo);
    Result save(RoomDto roomDto);
}
