package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void createCategory(Category category){
       categoryRepository.save(category);
    }
}
