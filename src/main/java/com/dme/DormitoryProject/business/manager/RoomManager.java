package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IRoomService;
import com.dme.DormitoryProject.dtos.room.RoomDto;
import com.dme.DormitoryProject.dtos.room.RoomMapper;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentMapper;
import com.dme.DormitoryProject.entity.Room;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.repository.IRoomDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RoomManager extends BaseClass implements IRoomService {

    private IRoomDao roomDao;
    private IStudentDao studentDao;

    @Autowired
    public RoomManager(IRoomDao roomDao, IStudentDao studentDao){
        this.roomDao=roomDao;
        this.studentDao=studentDao;
    }

    @Override
    public Result getAll() {
        try {
            List<RoomDto> roomDtoList = entityToDtoList(roomDao.findAll(), RoomMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,roomDtoList);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result getByRoomNo(Long roomNo) {
        Map<String , Object> roomAndStudents = new HashMap<>();
        try {
            Room room = roomDao.getByRoomNo(roomNo);
            Long roomId = room.getId();
            List<StudentDTO> studentDTOS = entityToDtoList(roomDao.getByRoomNoForStudent(roomId), StudentMapper::toDto);

            roomAndStudents.put("roomNo",room.getRoomNo());
            roomAndStudents.put("roomSize",room.getRoomSize());
            roomAndStudents.put("manySize",room.getManySize());
            roomAndStudents.put("perPersonSize",room.getPerPersonPrice());
            roomAndStudents.put("isFull",room.isFull());
            roomAndStudents.put("students",studentDTOS);

            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,roomAndStudents);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }


    @Override
    public Result save(RoomDto roomDto) {
        List<Room> roomList = roomDao.findAll();
        if (uniqueControl(roomList,roomDto,"getRoomNo")){
            return new ErrorResult(JsonFileReader.getMessage("502","tr"),false);
        }
        roomDao.save(dtoToEntity(roomDto,RoomMapper::toEntity));
        return new SuccessDataResult(JsonFileReader.getMessage("201","tr"),true,roomDto);
    }

    @Override
    public Result addStudentsToRoom(Long studentId, Long roomNo) {
        Room room = roomDao.getByRoomNo(roomNo);
        Student student = studentDao.getById(studentId);

        if (room.isFull()){
            return new ErrorResult(JsonFileReader.getMessage("567","tr"),false);
        }

        room.setManySize(room.getManySize() + 1);

        if (room.getManySize() == room.getRoomSize()){
            room.setFull(true);
        }

        student.setRoom(room);
        studentDao.save(student);
        roomDao.save(room);

        return new SuccesResult(JsonFileReader.getMessage("207","tr"),true);
    }
}
