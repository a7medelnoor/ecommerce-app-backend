package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.dto.cart.AddToCartDto;
import com.a7medelnoor.ecommerce_app.dto.cart.CartDto;
import com.a7medelnoor.ecommerce_app.dto.cart.CartItems;
import com.a7medelnoor.ecommerce_app.exceptions.CustomException;
import com.a7medelnoor.ecommerce_app.model.Cart;
import com.a7medelnoor.ecommerce_app.model.Product;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) {

        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        // save the cart
        cartRepository.save(cart);

    }

    public CartDto listCartItems(User user) {
        final List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        // change to cart dto
        List<CartItems> cartItems = new ArrayList<>();
        double totalPrice = 0;
        for (Cart cart: cartList){
            CartItems cartItemsDto = new CartItems(cart);
            totalPrice +=cartItemsDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemsDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(totalPrice);
        cartDto.setCartItems(cartItems);
        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        // check if the item id belongs to user
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()){
            throw new CustomException("Cart item id is invalid: " + cartItemId);
        }
        Cart cart = optionalCart.get();

        if (cart.getUser() != user){
            throw  new CustomException("Cart item doesnt belong to user: "+ cartItemId);
        }
        // else delete the cart
        cartRepository.delete(cart);
    }
}
