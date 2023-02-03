package com.example.projecttasc.services;

import com.example.projecttasc.database.entity.Book;
import com.example.projecttasc.database.entity.Category;
import com.example.projecttasc.database.repository.BookRepository;
import com.example.projecttasc.database.repository.CategoryRepository;
import com.example.projecttasc.model.dto.BookDto;
import com.example.projecttasc.model.request.BookRequest;
import com.example.projecttasc.model.response.AllBookReponse;
import com.example.projecttasc.model.response.BaseResponse;
import com.example.projecttasc.model.response.BookReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices {
    @Autowired
    BookRepository repository;
    @Autowired
    CategoryRepository categoryRepository;
    public BaseResponse createnewBook(Book book){
        BaseResponse baseResponse = new BaseResponse();
        if (book== null){
            baseResponse.setCode(400);
            baseResponse.setMess("not fond");
            return baseResponse;
        }
        Optional<Category> category= categoryRepository.findById(book.getCategoryid());
        if(category.isPresent()){
            System.out.println("đã tìm thấy");
            if(book.getTitle()==null|| book.getTitle()=="" ||
                    book.getAuthor()==null||book.getDescription()==null
                    ||book.getPrice()<=0||book.getPrice()==null
                    ||book.getThumbnail()==null) {
                baseResponse.setCode(400);
                baseResponse.setMess("hãy nhập đủ trường");
                return baseResponse;
            }else{
                repository.save(book);
            }
        }
        else {
            baseResponse.setCode(400);
            baseResponse.setMess("không thể tìm thấy category");
            return baseResponse;
        }
        return baseResponse;
    }

    public BookReponse getoneproduct(long id){
        BookReponse reponse = new BookReponse();
        Book book= repository.findById(id).orElse(null);
        if (book== null){
            reponse.setCode(400);
            reponse.setMess("not fond");
            return reponse;
        }
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());
        dto.setPrice(book.getPrice());
        dto.setStatus(book.getStatus());
        dto.setDiscount(book.getDiscount());
        dto.setThumbnail(book.getThumbnail());
        dto.setCategory(categoryRepository.findCategoryName(book.getCategoryid()));
        reponse.setData(dto);
        return reponse;
    }
    public AllBookReponse getallList(){
        AllBookReponse reponse =new  AllBookReponse();
        List<Book> books = repository.findAll();
        List<BookDto> dtos = new ArrayList<>();
        for (Book book : books){
            BookDto dto = new BookDto();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setDescription(book.getDescription());
            dto.setPrice(book.getPrice());
            dto.setStatus(book.getStatus());
            dto.setDiscount(book.getDiscount());
            dto.setThumbnail(book.getThumbnail());
            dto.setCategory(categoryRepository.findCategoryName(book.getCategoryid()));
            dtos.add(dto);
        }
        reponse.setData(dtos);
        return reponse;
    }
    public BookReponse update(long id, BookRequest request) {
        BookReponse reponse = new BookReponse();
        Book book = repository.findById(id).orElse(null);
        if (book == null) {
            reponse.setCode(400);
            reponse.setMess("book not fond");
            return reponse;
        }
        Optional<Category> category = categoryRepository.findById(book.getCategoryid());
        if (!category.isPresent()) {
            if (request.getAuthor() == null || request.getDescription() == null
                    || request.getTitle() == null || request.getPrice() <= 0 ||
                    request.getDiscount() <= -1 || request.getThumbnail() == null) {
                reponse.setCode(400);
                reponse.setMess("không nhập đủ trường");
                return reponse;
            }
            book.setAuthor(request.getAuthor());
            book.setDescription(request.getDescription());
            book.setTitle(request.getTitle());
            book.setCategoryid(request.getCategoryid());
            book.setDiscount(request.getDiscount());
            book.setThumbnail(request.getThumbnail());
            book.setPrice(request.getPrice());
            book.setStatus(request.getStatus());
            repository.save(book);
            BookDto dto = new BookDto();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setDescription(book.getDescription());
            dto.setPrice(book.getPrice());
            dto.setStatus(book.getStatus());
            dto.setDiscount(book.getDiscount());
            dto.setCategory(categoryRepository.findCategoryName(book.getCategoryid()));
            reponse.setData(dto);
            return reponse;
        } else {
            reponse.setCode(400);
            reponse.setMess("category not fond");
            return reponse;
        }
    }


}

