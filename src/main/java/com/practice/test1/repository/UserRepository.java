package com.practice.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.domen.User;

public interface UserRepository extends JpaRepository<User, Long>{
	

}
