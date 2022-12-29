package com.example.projecttasc.database.repository;

import com.example.projecttasc.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface UserRepository extends JpaRepository<User, Long> {

@Query(value = "select * from user where username = ?1",nativeQuery = true)
    User findByUserName (String username);
}
