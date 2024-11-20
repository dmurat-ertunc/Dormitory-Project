package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface IStudentDao extends JpaRepository<Student,Long> {

    //Student findStudentById(Long id);
    // List<Student> findByEmailEndingWith(String emailDomain);
    //@Query("SELECT s FROM Student s WHERE YEAR(CURRENT_DATE) - YEAR(s.birthDate) > 18")    // CREATE QUERY
    @Query(value = "SELECT * FROM studentTbl WHERE TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) > 18", nativeQuery = true)//NATİVE QUERY
    List<Student> findStudentsOlderThan18Native();
    @Query("SELECT d FROM Student d WHERE d.isDeleted = false")
    List<Student> findAll();
    List<Student> findByUniversityId(Long universityId);
    List<Student> findByUniversity_Id(Long universityId);
    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student findStudentById(@Param("id") Long id);
    Student findByMail(String mail);
    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN s.punishments p WHERE p IS NULL OR p.punishmentTime < :thirtyDaysAgo")
    List<Student> findStudentsWithNoPunishmentsInLast30Days(@Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo);


}
