package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomDao extends JpaRepository<Room,Long> {
}
