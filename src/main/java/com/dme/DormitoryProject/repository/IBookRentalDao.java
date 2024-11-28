package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRentalDao extends JpaRepository<BookRental,Long> {
    List<BookRental> findByStudentId(Long id);
    List<BookRental> findByBookId(Long id);
}
