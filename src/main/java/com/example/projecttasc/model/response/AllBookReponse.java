package com.example.projecttasc.model.response;

import com.example.projecttasc.model.dto.BookDto;
import lombok.Data;

import java.util.List;
@Data
public class AllBookReponse extends BaseResponse{
    private List<BookDto> data;

    public AllBookReponse() {
    }
}
