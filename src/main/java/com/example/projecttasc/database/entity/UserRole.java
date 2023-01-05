package com.example.projecttasc.database.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "user_id")
    private Long Userid;
    @Column(name = "role_id")
    private int Roleid;
}
