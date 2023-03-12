package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.dto.ProductDto;
import com.a7medelnoor.ecommerce_app.exceptions.ProductNotExistException;
import com.a7medelnoor.ecommerce_app.model.Category;
import com.a7medelnoor.ecommerce_app.model.Product;
import com.a7medelnoor.ecommerce_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }

    public void createProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);

    }

    // convert product to product dto
    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product : allProducts) {
            productDto.add(getProductDto(product));
        }
        return productDto;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        // get the product
        Optional<Product> optionalProduct = productRepository.findById(productId);

        // throw  an exception if the product doesn't exist
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not peresent");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public Product findById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistException("Product is invalid " + productId);
        }
        return optionalProduct.get();
    }
}
