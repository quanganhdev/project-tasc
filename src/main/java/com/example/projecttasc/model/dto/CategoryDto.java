package com.example.projecttasc.model.dto;

import com.example.projecttasc.database.entity.Book;
import lombok.Data;

import java.util.List;
@Data
public class CategoryDto {
    private long Id;
    private String Name;
    private String Description;
    private int Status;
    private List<Book> books;

    public CategoryDto() {
    }

    public CategoryDto(long id, String name, String description, int status) {
        Id = id;
        Name = name;
        Description = description;
        Status = status;
    }

    public CategoryDto(long id, String name, String description, int status, List<Book> books) {
        Id = id;
        Name = name;
        Description = description;
        Status = status;
        this.books = books;
    }
}
