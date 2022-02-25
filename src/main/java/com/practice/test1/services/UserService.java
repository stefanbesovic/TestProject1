package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.User;

public interface UserService {
	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}
