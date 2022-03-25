package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.User;
import com.practice.test1.entities.UserRole;

public interface UserService {
	User saveUser(User user);
	User addRoleToUser(String username, String name);
	List<User> getAllUsers();
	User getUserById(Long id);
	User updateUser(User user, Long id);
	void deleteUser(Long id);
	User findByUsername(String username);
}
