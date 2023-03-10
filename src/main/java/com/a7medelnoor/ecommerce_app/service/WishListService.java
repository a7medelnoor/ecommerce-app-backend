package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.dto.ProductDto;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.model.WishList;
import com.a7medelnoor.ecommerce_app.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    ProductService productService;

    public void saveItemToWithList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getAllWishListForUser(User user) {
        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        // find the product dto
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }
        return productDtos;
    }
}
