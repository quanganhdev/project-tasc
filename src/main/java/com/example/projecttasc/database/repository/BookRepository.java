package com.example.projecttasc.database.repository;

import com.example.projecttasc.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from book where categoryid = ?1", nativeQuery = true)
    List<Book> findAllByCategory(long categoryid);

}
