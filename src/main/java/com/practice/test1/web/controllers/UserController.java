package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.entities.UserRole;
import com.practice.test1.web.dto.role.RoleToUserForm;
import com.practice.test1.web.dto.role.UserRoleDto;
import com.practice.test1.web.dto.role.UserRoleMapper;
import com.practice.test1.web.dto.user.UserDto;
import com.practice.test1.web.dto.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.practice.test1.entities.User;
import com.practice.test1.services.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
	
	private final UserService userService;

	@PostMapping()
	public UserDto saveUser(@Valid @RequestBody User user) {
		userService.saveUser(user);
		return UserMapper.INSTANCE.toDto(user);
	}

	@PostMapping("/roles")
	public UserRoleDto saveRole(@Valid @RequestBody UserRole role) {
		return UserRoleMapper.INSTANCE.toDto(userService.saveRole(role));
	}

	@PostMapping("/add-role")
	public void addRoleToUser(@Valid @RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUsername(), form.getName());
	}

	@GetMapping()
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers()
				.stream()
				.map(UserMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		return UserMapper.INSTANCE.toDto(userService.getUserById(id));
	}
	
	@PutMapping("{id}")
	public UserDto updateUser(@PathVariable("id") long id, @Valid @RequestBody UserDto userDto) {
		userService.updateUser(UserMapper.INSTANCE.fromDto(userDto), id);
		return userDto;
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}
}

