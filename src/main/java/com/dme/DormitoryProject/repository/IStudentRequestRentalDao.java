package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.dtos.studentRentalDtos.StudentRequestRentalDTO;
import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IStudentRequestRentalDao extends JpaRepository<StudentRequestRental,Long> {
    //void save(StudentRequestRentalDTO studentRequestRentalDTO);
    @Query("SELECT s FROM SportArea s WHERE s.id IN (" +
            "SELECT r.sportArea.id FROM Rental r " +
            "WHERE ((r.startTime BETWEEN :startTime AND :endTime) " +
            "OR (:startTime BETWEEN r.startTime AND r.endTime)) " +
            "AND r.rentalDate = :rentalDate)")
    List<SportArea> findOverlappingRentals(@Param("startTime") LocalTime startTime,
                                           @Param("endTime") LocalTime endTime,
                                           @Param("rentalDate") LocalDate rentalDate);
}
