package com.a7medelnoor.ecommerce_app.controller;

import com.a7medelnoor.ecommerce_app.common.ApiResponse;
import com.a7medelnoor.ecommerce_app.dto.ProductDto;
import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.model.Product;
import com.a7medelnoor.ecommerce_app.repository.CategoryRepository;
import com.a7medelnoor.ecommerce_app.service.CategoryService;
import com.a7medelnoor.ecommerce_app.service.ProductService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryService;



    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        // check if we have the category
        Optional<Category> optionalCategory = categoryService.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exists"), HttpStatus.CONFLICT);
        }
        // create the product
        Category category = optionalCategory.get();
        productService.createProduct(productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // an api to edit product
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        // check if we have the category
        Optional<Category> optionalCategory = categoryService.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category doesn't exists"), HttpStatus.BAD_REQUEST);
        }
        // create the product
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK
        );

    }
}
