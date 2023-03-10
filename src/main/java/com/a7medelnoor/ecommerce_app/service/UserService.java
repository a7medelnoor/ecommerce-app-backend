package com.a7medelnoor.ecommerce_app.service;


import com.a7medelnoor.ecommerce_app.dto.ResponseDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignInDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignInResponseDto;
import com.a7medelnoor.ecommerce_app.dto.user.SignUpDto;
import com.a7medelnoor.ecommerce_app.exceptions.AuthenticationFailedException;
import com.a7medelnoor.ecommerce_app.exceptions.CustomException;
import com.a7medelnoor.ecommerce_app.model.AuthenticationToken;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.repository.UserServiceRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {


    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto singUp(SignUpDto signUpDto) {
        // check if user is already present
        if (Objects.nonNull(userServiceRepository.findByEmail(signUpDto.getEmail()))) {
            // we have a user
            throw new CustomException("user already present");
        }
        // hash the password
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // save the user
        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(), encryptedPassword);

        userServiceRepository.save(user);

        // create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        // save the token
        authenticationService.saveConfirmationToken(authenticationToken);
        ResponseDto responseDto = new ResponseDto("Success", "User Created Successfully");

        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto singIn(SignInDto signInDto) {
        // check if the user exist by email
        User user = userServiceRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailedException("user is not valid");
        }
        // hash the password
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailedException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // if there is match compare the password in DB

        AuthenticationToken token = authenticationService.getToken(user);
        // retrieve the token

        if (Objects.isNull(token)) {
            throw new CustomException("Token is not present");
        }

        return new SignInResponseDto("Success", token.getToken());
    }
}
