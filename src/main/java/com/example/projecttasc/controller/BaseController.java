package com.example.projecttasc.controller;
import com.example.projecttasc.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<BaseResponse> createdResponse(BaseResponse response){
        return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
    }
    public ResponseEntity<BaseResponse> createdResponse(BaseResponse response, HttpStatus httpStatus){
        return new ResponseEntity<>(response , httpStatus);
    }
    public ResponseEntity<CategoryRespone> createdCategoryResponse(CategoryRespone response, HttpStatus ok){
        return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
    }
    public ResponseEntity<CategoryProductResponse> createcateproduct (CategoryProductResponse response, HttpStatus ok){
        return new ResponseEntity<CategoryProductResponse>(response , HttpStatus.ACCEPTED);
    }
    public ResponseEntity<BookReponse> createdBookgetReponse (BookReponse response, HttpStatus ok){
        return new ResponseEntity<BookReponse>(response , HttpStatus.ACCEPTED);
    }
    public ResponseEntity<AllBookReponse> createallbookreponse (AllBookReponse response, HttpStatus ok){
        return new ResponseEntity<AllBookReponse>(response , HttpStatus.ACCEPTED);
    }
}


