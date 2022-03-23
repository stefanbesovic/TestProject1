package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.role.RoleUserGet;
import com.practice.test1.web.dto.user.UserDto;
import com.practice.test1.web.dto.user.UserMapper;
import com.practice.test1.web.dto.user.UserRegisterDto;
import com.practice.test1.web.dto.user.UserRegisterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.practice.test1.entities.User;
import com.practice.test1.services.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {
	
	private final UserService userService;

	@PostMapping()
	public UserDto saveUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
		User user = userService.saveUser(UserRegisterMapper.INSTANCE.fromDto(userRegisterDto));
		return UserMapper.INSTANCE.toDto(user);
	}

	@PostMapping("/add-role")
	public UserDto addRoleToUser(@Valid @RequestBody RoleUserGet form) {
		User user = userService.addRoleToUser(form.getUsername(), form.getName());
		return UserMapper.INSTANCE.toDto(user);
	}

	@GetMapping("/all")
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
	public UserDto updateUser(@PathVariable("id") long id,
							  @Valid @RequestBody UserDto userDto) {
		userService.updateUser(UserMapper.INSTANCE.fromDto(userDto), id);
		return userDto;
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}
}

