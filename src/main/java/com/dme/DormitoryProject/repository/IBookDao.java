package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookDao extends JpaRepository<Book,Long> {
    List<Book> findByNameContaining(String name);
    @Query("SELECT d FROM Book d WHERE d.isEmpty = true")
    List<Book> emptyBook();
}
