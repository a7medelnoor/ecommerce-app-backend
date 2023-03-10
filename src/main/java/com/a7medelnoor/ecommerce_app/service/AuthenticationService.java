package com.a7medelnoor.ecommerce_app.service;

import com.a7medelnoor.ecommerce_app.model.AuthenticationToken;
import com.a7medelnoor.ecommerce_app.model.User;
import com.a7medelnoor.ecommerce_app.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;

    public  AuthenticationToken getToken(User user) {
      return  tokenRepository.findByUser(user);
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
}
