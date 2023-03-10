package com.a7medelnoor.ecommerce_app.exceptions;

public class CustomException extends IllegalArgumentException{

    public CustomException(String msg){
        super(msg);
    }
}
