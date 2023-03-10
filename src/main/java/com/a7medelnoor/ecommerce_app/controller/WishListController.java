package com.a7medelnoor.ecommerce_app.controller;

import com.a7medelnoor.ecommerce_app.common.ApiResponse;
import com.a7medelnoor.ecommerce_app.dto.ProductDto;
import com.a7medelnoor.ecommerce_app.model.Product;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.model.WishList;
import com.a7medelnoor.ecommerce_app.service.AuthenticationService;
import com.a7medelnoor.ecommerce_app.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    AuthenticationService authenticationService;

    // save product in whishlist
    @PostMapping("/addWishList")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        // save the item in wish list
        WishList wishList = new WishList(user, product);
        wishListService.saveItemToWithList(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wish list");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // get all wishlist item for a user

    @PostMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getAllWishList(@PathVariable("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);

        // get all wish list
        List<ProductDto> productDtos = wishListService.getAllWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}
