package com.practice.test1.web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.entities.UserRole;
import com.practice.test1.web.dto.user.UserDto;
import com.practice.test1.web.dto.user.UserMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/roles")
	public UserRole saveRole(@RequestBody UserRole role) {
		return userService.saveRole(role);
	}

	@PostMapping("/add-role")
	public void addRoleToUser(@RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUsername(), form.getName());
	}

	@GetMapping()
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers()
				.stream()
				.map(user -> UserMapper.INSTANCE.toDto(user))
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
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

@Data
class RoleToUserForm {
	private String username;
	private String name;
}