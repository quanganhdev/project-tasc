package com.example.projecttasc.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "category")
@Data
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String Name;
    @Column(name = "description")
    private String Description;
    @Column(name = "status")
    private int status;
//    @Column(name = "categoryid")
//    private long Categoryid;
//    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SELECT)
//    private Set<Book> books;

}
