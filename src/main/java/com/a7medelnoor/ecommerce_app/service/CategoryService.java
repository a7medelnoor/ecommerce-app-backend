package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.*;
import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void createCategory(Category category){
       categoryRepository.save(category);
    }
    public List<Category> getListCategory(){
        return categoryRepository.findAll();
    }
}
