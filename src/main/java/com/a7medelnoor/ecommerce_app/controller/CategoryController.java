package com.a7medelnoor.ecommerce_app.controller;

import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
     categoryService.createCategory(category);
     return "Success";
    }
    @GetMapping("/list")
    public List<Category> getCategory(){
        return categoryService.getListCategory();

    }
    @PostMapping("/update/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        categoryService.updateCategory(categoryId, category);
        return "Success";
    }
}
