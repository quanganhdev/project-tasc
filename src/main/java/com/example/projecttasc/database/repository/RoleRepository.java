package com.example.projecttasc.database.repository;

import com.example.projecttasc.database.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT r from  role  r where r.Name= ?1")
    Role findByName(String name);
}
