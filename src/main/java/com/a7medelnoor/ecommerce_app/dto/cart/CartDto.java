package com.a7medelnoor.ecommerce_app.dto.cart;

import java.util.List;

public class CartDto {
    private List<CartItems> cartItems;
    private double totalPrice;

    public CartDto() {
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
