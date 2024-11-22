package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Room;
import com.dme.DormitoryProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomDao extends JpaRepository<Room,Long> {
    Room getByRoomNo(Long roomNo);
    @Query("SELECT s FROM Student s WHERE s.room.Id = :roomId")
    List<Student> getByRoomNoForStudent(Long roomId);
    @Query("SELECT r FROM Room r WHERE r.isFull = false")
    List<Room> emptyRoom();
    @Query("SELECT r FROM Room r WHERE r.isDeleted = false")
    List<Room> findAll();
}
