package com.example.projecttasc.database.repository;

import com.example.projecttasc.database.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select name from category where id = ?1", nativeQuery = true)
    String findCategoryName(long id);
}
