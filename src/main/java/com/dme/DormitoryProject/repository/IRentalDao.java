package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IRentalDao extends JpaRepository<Rental,Long> {
    Rental findRentalById(Long id);
    List<Rental> findBySportAreaIdAndRentalDate(Long id, LocalDate date);
    List<Rental> findByStartTimeAfter(LocalTime startTime);
    @Query("SELECT s FROM SportArea s WHERE s.id IN (" +
            "SELECT r.sportArea.id FROM Rental r " +
            "WHERE ((r.startTime BETWEEN :startTime AND :endTime) " +
            "OR (:startTime BETWEEN r.startTime AND r.endTime)) " +
            "AND r.rentalDate = :rentalDate)")
    List<SportArea> findOverlappingRentals(@Param("startTime") LocalTime startTime,
                                           @Param("endTime") LocalTime endTime,
                                           @Param("rentalDate") LocalDate rentalDate);

    List<Rental> findByStudentId(Long id);
    List<Rental> findBySportAreaId(Long id);



//    SELECT s FROM SportArea s " +
//            "JOIN s.rentalList r " +
//            "WHERE ((r.startTime BETWEEN :startTime AND :endTime) " +
//            "OR (:startTime BETWEEN r.startTime AND r.endTime)) " +
//            "AND r.rentalDate = :rentalDate " +
//            "AND s.id = r.sportArea.id
    // select * from users where username = 'alter table user'
    // native query bu yüzden güvenlik açığı sahibidir
    // hibernate query kullan
}
