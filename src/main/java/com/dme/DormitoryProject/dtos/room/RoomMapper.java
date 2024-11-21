package com.dme.DormitoryProject.dtos.room;

import com.dme.DormitoryProject.entity.Room;

public class RoomMapper {

    public static RoomDto toDto(Room room){

        RoomDto roomDto = new RoomDto();

        roomDto.setRoomNo(room.getRoomNo());
        roomDto.setFull(room.isFull());
        roomDto.setRoomSize(room.getRoomSize());
        roomDto.setManySize(room.getManySize());
        roomDto.setPerPersonSize(room.getPerPersonPrice());

        return roomDto;
    }
    public static Room toEntity(RoomDto roomDto){

        Room room = new Room();

        room.setRoomNo(roomDto.getRoomNo());
        room.setFull(roomDto.isFull());
        room.setRoomSize(roomDto.getRoomSize());
        room.setManySize(roomDto.getManySize());
        room.setPerPersonPrice(roomDto.getPerPersonSize());

        return room;
    }
}
