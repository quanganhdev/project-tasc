package com.example.projecttasc.controller;

import com.example.projecttasc.model.request.CategoryRequest;
import com.example.projecttasc.model.response.BaseResponse;
import com.example.projecttasc.model.response.CategoryProductResponse;
import com.example.projecttasc.model.response.CategoryRespone;
import com.example.projecttasc.services.CategoryServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Autowired
    CategoryServies categoryServies;
    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CategoryRequest request){
        return createdResponse(categoryServies.createcategory(request),HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<CategoryRespone> getallcategory(){
        return createdCategoryResponse(categoryServies.getallcategory(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable long id, @RequestBody CategoryRequest request){
        return createdResponse(categoryServies.updatecategory(id,request));
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<CategoryProductResponse> getcategoryproduct(@PathVariable long id){
       return createcateproduct(categoryServies.getbookofcategory(id),HttpStatus.OK);
    };
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable long id){
        return createdResponse(categoryServies.Delete(id));
    }

}
