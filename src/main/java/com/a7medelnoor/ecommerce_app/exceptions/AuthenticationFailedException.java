package com.a7medelnoor.ecommerce_app.exceptions;

public class AuthenticationFailedException extends IllegalArgumentException {
    public AuthenticationFailedException(String msg){
        super(msg);
    }
}
