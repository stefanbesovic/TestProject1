package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.User;
import com.practice.test1.entities.UserRole;

public interface UserService {
	User saveUser(User user);
	UserRole saveRole(UserRole role);
	void addRoleToUser(String username, String name);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}
