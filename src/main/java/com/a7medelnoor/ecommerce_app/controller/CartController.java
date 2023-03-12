package com.a7medelnoor.ecommerce_app.controller;

import com.a7medelnoor.ecommerce_app.common.ApiResponse;
import com.a7medelnoor.ecommerce_app.dto.cart.AddToCartDto;
import com.a7medelnoor.ecommerce_app.dto.cart.CartDto;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.service.AuthenticationService;
import com.a7medelnoor.ecommerce_app.service.CartService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthenticationService authenticationService;


    // add cart
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);

        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete all cart items for a user
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItems(@PathVariable("cartItemId")Integer cartItemId,
                                                   @RequestParam("token") String token){
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(cartItemId, user);

return new ResponseEntity<>(new ApiResponse(true,"Item has been removed"),HttpStatus.OK);
    }
}
