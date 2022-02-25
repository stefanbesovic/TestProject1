package com.practice.test1.web.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.entities.User;
import com.practice.test1.services.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
	
	private final UserService userService;

	@PostMapping()
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping()
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("{id}")
	public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
		return userService.updateUser(user, id);
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}
}
