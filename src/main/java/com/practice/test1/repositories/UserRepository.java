package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
