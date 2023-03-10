package com.a7medelnoor.ecommerce_app.controller;


import com.a7medelnoor.ecommerce_app.dto.ResponseDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignInDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignInResponseDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignUpDto;
import com.a7medelnoor.ecommerce_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;

    // sing up
    @PostMapping("/signUp")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto) {
        return userService.singUp(signUpDto);
    }


    // sign in
    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.singIn(signInDto);
    }

}
