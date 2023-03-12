package com.a7medelnoor.ecommerce_app.dto.cart;

import com.a7medelnoor.ecommerce_app.model.Cart;
import com.a7medelnoor.ecommerce_app.model.Product;

public class CartItems {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItems() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public CartItems(Cart cart){
        this.id=cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());
    }
}
