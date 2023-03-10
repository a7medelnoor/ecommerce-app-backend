package com.a7medelnoor.ecommerce_app.repository;

import com.a7medelnoor.ecommerce_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserServiceRepository extends JpaRepository<User, Integer> {

    User  findByEmail(String email);
}
