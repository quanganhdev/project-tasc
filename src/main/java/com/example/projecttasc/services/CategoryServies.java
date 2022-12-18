package com.example.projecttasc.services;

import com.example.projecttasc.database.entity.Book;
import com.example.projecttasc.database.entity.Category;
import com.example.projecttasc.database.repository.BookRepository;
import com.example.projecttasc.database.repository.CategoryRepository;
import com.example.projecttasc.model.dto.CategoryDto;
import com.example.projecttasc.model.request.CategoryRequest;
import com.example.projecttasc.model.response.BaseResponse;

import com.example.projecttasc.model.response.CategoryProductResponse;
import com.example.projecttasc.model.response.CategoryRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServies {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookRepository bookRepository;
    @Transactional
    public BaseResponse createcategory(CategoryRequest request){
        BaseResponse response = new BaseResponse();
        if(request.getDescription() == null || request.getDescription()=="" ){
            response.setCode(402);
            response.setMess("description bị bỏ trống");
            return response;
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());
        categoryRepository.save(category);
        return response;
    }
    public CategoryProductResponse getbookofcategory(Long id){
        CategoryProductResponse categoryProductResponse = new  CategoryProductResponse();
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setStatus(category.getStatus());
            List <Book> books = new ArrayList<>();
            books= bookRepository.findAllByCategory(category.getId());
            categoryDto.setBooks(books);
            categoryProductResponse.setData(categoryDto);
            return categoryProductResponse;
        }else{
             categoryProductResponse.setCode(400);
             categoryProductResponse.setMess("category not fond");
             return categoryProductResponse;
        }


    }
    public BaseResponse updatecategory(long id, CategoryRequest request){
        BaseResponse baseResponse = new BaseResponse();
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category category1 = category.get();
            category1.setName(request.getName());
            category1.setDescription(request.getDescription());
            category1.setStatus(request.getStatus());
            categoryRepository.save(category1);
        }else {
            baseResponse.setCode(400);
            baseResponse.setMess("không tìm thấy category");
            return baseResponse;
        }
        return baseResponse;
    }
    public CategoryRespone getallcategory(){
        CategoryRespone respone = new CategoryRespone();
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()){
            respone.setCode(400);
            respone.setMess("không tìm thấy category nào");
            respone.setData(categoryList);
        }
        respone.setData(categoryList);
        return respone;
    }
    public BaseResponse Delete(long id){
        BaseResponse baseResponse = new BaseResponse();
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category ct = category.get();
            categoryRepository.delete(ct);
            return baseResponse;
        }
        baseResponse.setMess("không tìm thấy category");
        baseResponse.setCode(400);
        return baseResponse;
    }
}
