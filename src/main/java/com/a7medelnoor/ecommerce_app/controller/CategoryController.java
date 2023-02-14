package com.a7medelnoor.ecommerce_app.controller;

import com.a7medelnoor.ecommerce_app.common.ApiResponse;
import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){

     categoryService.createCategory(category);
     return new ResponseEntity<>(new ApiResponse(true,"a new category successfully created"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<Category> getCategory(){
        return categoryService.getListCategory();

    }
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false, "category id doesn't exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "category updated successfully"), HttpStatus.OK);
    }
}
