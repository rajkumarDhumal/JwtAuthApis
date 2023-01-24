package com.jwtAuthapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtAuthapi.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
