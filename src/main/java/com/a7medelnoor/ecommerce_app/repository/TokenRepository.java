package com.a7medelnoor.ecommerce_app.repository;

import com.a7medelnoor.ecommerce_app.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
}
