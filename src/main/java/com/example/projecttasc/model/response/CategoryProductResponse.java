package com.example.projecttasc.model.response;

import com.example.projecttasc.model.dto.CategoryDto;
import lombok.Data;

@Data
public class CategoryProductResponse extends BaseResponse{
    private CategoryDto data;

    public CategoryProductResponse(int code, String mess, CategoryDto data) {
        super(code, mess);
        this.data = data;
    }
    public CategoryProductResponse(){

    };
}
