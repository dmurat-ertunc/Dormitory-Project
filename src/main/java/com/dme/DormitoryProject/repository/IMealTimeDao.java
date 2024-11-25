package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.MealTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IMealTimeDao extends JpaRepository<MealTime,Long> {
    @Query("SELECT m FROM MealTime m WHERE m.student.id = :studentId ORDER BY m.addDate DESC, m.eatTime DESC LIMIT 1")
    MealTime findLatestMealTimeByStudentId(Long studentId);
    boolean existsByStudentId (Long studentId);
}
