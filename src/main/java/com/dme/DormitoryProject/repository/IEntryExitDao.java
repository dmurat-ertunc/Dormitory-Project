package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.EntryExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IEntryExitDao extends JpaRepository<EntryExit,Long> {
    @Query("SELECT e FROM EntryExit e WHERE e.student.id = :studentId ORDER BY e.momentaryTime DESC LIMIT 1")
    Optional<EntryExit> findLatestEntryExitByStudentId(@Param("studentId") Long studentId);
    @Query(value = "SELECT e FROM EntryExit e WHERE e.student.id = :studentId")
    List<Optional<EntryExit>> existByStudentId(@Param("studentId") Long studentId);
}
