package com.example.projecttasc.controller.admin;

import com.example.projecttasc.controller.BaseController;
import com.example.projecttasc.database.entity.Book;
import com.example.projecttasc.model.request.BookRequest;
import com.example.projecttasc.model.response.AllBookReponse;
import com.example.projecttasc.model.response.BaseResponse;
import com.example.projecttasc.model.response.BookReponse;
import com.example.projecttasc.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/book")
public class BookAdminController extends BaseController {
    @Autowired
    BookServices services;
    @PostMapping
    public ResponseEntity<BaseResponse>createnewbook(@RequestBody Book book){
        return createdResponse(services.createnewBook(book), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookReponse> getone(@PathVariable long id){
        return createdBookgetReponse(services.getoneproduct(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<AllBookReponse> getall(){
        return createallbookreponse(services.getallList(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookReponse>updatebook(@PathVariable long id,@RequestBody BookRequest request){
        return createdBookgetReponse(services.update(id,request),HttpStatus.OK);
    }
}
