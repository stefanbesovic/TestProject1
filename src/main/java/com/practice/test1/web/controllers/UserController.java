package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.role.RoleUserGet;
import com.practice.test1.web.dto.user.UserDto;
import com.practice.test1.web.dto.user.UserMapper;
import com.practice.test1.web.dto.user.UserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.practice.test1.entities.User;
import com.practice.test1.services.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
@Tag(name = "User Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of User.")
public class UserController {
	
	private final UserService userService;

	@Operation(summary = "Creates new User")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping()
	public UserDto saveUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
		User user = userService.saveUser(UserMapper.INSTANCE.fromRegDto(userRegisterDto));
		return UserMapper.INSTANCE.toDto(user);
	}

	@Operation(summary = "Assign new Role to User")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "User or Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping("/add-role")
	public UserDto addRoleToUser(@Valid @RequestBody RoleUserGet form) {
		User user = userService.addRoleToUser(form.getUsername(), form.getName());
		return UserMapper.INSTANCE.toDto(user);
	}

	@Operation(summary = "Retrieves list of all Users")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "List of Users"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/all")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers()
				.stream()
				.map(UserMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Retrieves details about User")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		return UserMapper.INSTANCE.toDto(userService.getUserById(id));
	}

	@Operation(summary = "Updates existing User")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PutMapping("{id}")
	public UserDto updateUser(@PathVariable("id") Long id,
							  @Valid @RequestBody UserDto userDto) {
		User user = userService.updateUser(UserMapper.INSTANCE.fromDto(userDto), id);
		return UserMapper.INSTANCE.toDto(user);
	}

	@Operation(summary = "Deletes User")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User deleted"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
}

