package com.example.projecttasc.database.entity;

import javax.persistence.*;

//@Entity(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Column(name = "user_id")
    private Long Userid;
    @Column(name = "role_id")
    private int Roleid;
}
