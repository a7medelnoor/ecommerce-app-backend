package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.exceptions.AuthenticationFailedException;
import com.a7medelnoor.ecommerce_app.model.AuthenticationToken;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;

    public  AuthenticationToken getToken(User user) {
      return  tokenRepository.findByUser(user);
    }

    public User getUser(String token){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)){
            return null;
        }
      // authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
    // authenticate the token

    public void authenticate(String token){
        // check if the token is valid or not
        if (Objects.isNull(token)){
            // throw an exceptiopn
            throw  new AuthenticationFailedException("token not present");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthenticationFailedException("token is not valid");
        }
    }
}
