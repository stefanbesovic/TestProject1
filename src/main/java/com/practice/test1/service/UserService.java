package com.practice.test1.service;

import java.util.List;
import java.util.Set;

import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.User;

public interface UserService {
	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}
