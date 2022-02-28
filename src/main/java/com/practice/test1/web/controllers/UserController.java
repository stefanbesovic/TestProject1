package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.user.UserDto;
import com.practice.test1.web.dto.user.UserMapper;
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
	public User saveUser(@RequestBody UserDto userDto) {
		return userService.saveUser(UserMapper.INSTANCE.fromDto(userDto));
	}

	@GetMapping()
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers()
				.stream()
				.map(user -> UserMapper.INSTANCE.toDto(user))
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable("id") long id) {
		return UserMapper.INSTANCE.toDto(userService.getUserById(id));
	}
	
	@PutMapping("{id}")
	public User updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
		return userService.updateUser(UserMapper.INSTANCE.fromDto(userDto), id);
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}
}
