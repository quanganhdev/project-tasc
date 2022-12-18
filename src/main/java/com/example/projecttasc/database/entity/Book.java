package com.example.projecttasc.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Title;
    private Double Price;
    private String Description;
    private String Author;
    private int Status;
    private int Discount;
    private String Thumbnail;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SELECT)
    @Column(name = "categoryid")
    private long Categoryid;
    public Book() {
    }
    public Book(long id, String title, Double price, String description, int status, int discount, String thumbnail,long categoryid) {
        Id = id;
        Title = title;
        Price = price;
        Description = description;
        Status = status;
        Discount = discount;
        Thumbnail= thumbnail;
        Categoryid = categoryid;
    }
}
