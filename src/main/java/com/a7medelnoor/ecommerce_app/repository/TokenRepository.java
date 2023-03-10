package com.a7medelnoor.ecommerce_app.repository;

import com.a7medelnoor.ecommerce_app.model.AuthenticationToken;
import com.a7medelnoor.ecommerce_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);

}
