package com.example.projecttasc.database.repository;

import com.example.projecttasc.database.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select * from  role where name = ?1",nativeQuery = true)
    Role findByName(String name);
}
