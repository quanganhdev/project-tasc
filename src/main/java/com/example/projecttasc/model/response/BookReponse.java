package com.example.projecttasc.model.response;

import com.example.projecttasc.model.dto.BookDto;
import lombok.Data;

@Data
public class BookReponse extends BaseResponse{
    private BookDto data;

    public BookReponse(int code, String mess, BookDto data) {
        super(code, mess);
        this.data = data;
    }

    public BookReponse() {
    }
}
