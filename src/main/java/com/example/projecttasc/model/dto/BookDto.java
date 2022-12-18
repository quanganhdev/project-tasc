package com.example.projecttasc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private long Id;
    private String Title;
    private Double Price;
    private String Description;
    private String Author;
    private int Status;
    private int Discount;
    private String Thumbnail;
    private String Category;

    public BookDto() {

    }
}
