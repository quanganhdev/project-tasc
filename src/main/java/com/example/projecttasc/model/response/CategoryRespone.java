package com.example.projecttasc.model.response;

import com.example.projecttasc.database.entity.Category;

import lombok.Data;
import java.util.List;

@Data
public class CategoryRespone extends BaseResponse{
    private List<Category> data;

    public CategoryRespone(int code, String mess) {
        super(code, mess);
    }

    public CategoryRespone() {
    }

    public CategoryRespone(int code, String mess, List<Category> data) {
        super(code, mess);
        this.data = data;
    }

}
