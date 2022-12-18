package com.example.projecttasc.model.request;

import lombok.Data;
@Data
public class BookRequest {
    private String Title;
    private Double Price;
    private String Description;
    private String Author;
    private int Status;
    private int Discount;
    private String Thumbnail;
    private long Categoryid;
}
