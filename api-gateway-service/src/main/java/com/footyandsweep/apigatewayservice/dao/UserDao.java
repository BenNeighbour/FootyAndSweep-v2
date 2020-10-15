package com.footyandsweep.apigatewayservice.dao;

import com.footyandsweep.apigatewayservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

    User findUserByUserId(UUID userId);

}
