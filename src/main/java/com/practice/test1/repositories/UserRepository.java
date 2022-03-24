package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
