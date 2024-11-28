package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;

import com.dme.DormitoryProject.entity.Room;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.RoomMg;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IRoomMgDao;
import com.dme.DormitoryProject.repository.IRoomDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomDataMig {

    private IRoomDao roomDao;
    private IRoomMgDao roomMgDao;
    private IStudentDao studentDao;

    @Autowired
    public RoomDataMig(IRoomDao roomDao, IRoomMgDao roomMgDao, IStudentDao studentDao) {
        this.roomDao = roomDao;
        this.roomMgDao = roomMgDao;
        this.studentDao=studentDao;
    }

    @Scheduled(cron = "00 41 11 * * ?")
    public void maigration(){
        List<Room> roomList = roomDao.findAll();

        for (Room room : roomList){
            if (!room.isThrowMongo()){
                RoomMg roomMg = new RoomMg();

                roomMg.setRoomId(room.getId());
                roomMg.setRoomNo(room.getRoomNo());
                roomMg.setRoomSize(room.getRoomSize());
                roomMg.setFull(room.isFull());
                roomMg.setManySize(room.getManySize());
                roomMg.setPerPersonPrice(room.getPerPersonPrice());
                roomMg.setAddDate(room.getAddDate());
                roomMg.setDeleted(room.isDeleted());
                roomMg.setStudents(studentDao.findByRoomId(room.getId()));

                room.setThrowMongo(true);

                roomDao.save(room);
                roomMgDao.save(roomMg);

            }
        }
    }
}
