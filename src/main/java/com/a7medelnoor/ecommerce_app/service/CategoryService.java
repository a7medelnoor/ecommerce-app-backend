package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.repository.CategoryRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void createCategory(Category category){
       categoryRepository.save(category);
    }
    public Category readCategory(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }
    public Optional<Category> readCategory(Integer categoryId){
        return categoryRepository.findById(categoryId);
    }
    public List<Category> getListCategory(){
        return categoryRepository.findAll();
    }

    public void editCategory(Integer categoryId, Category updateCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepository.save(category);
    }

    public boolean findById(int categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
