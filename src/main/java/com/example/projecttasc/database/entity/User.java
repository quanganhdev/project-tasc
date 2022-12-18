package com.example.projecttasc.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Data
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "user")
    private String Email;
    @Column(name = "full_name")
    private String FullName;
    @Column(name = "phone")
    private String Phone;
    @Column(name = "birthday")
    private Date Brithday;
    @Column(name="password")
    private  String Password;
    @Column(name = "address")
    private String Address;
    @Column(name = "username")
    private String UserName;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles = new HashSet();
    public User() {
    }

}