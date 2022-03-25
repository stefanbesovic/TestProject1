package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import com.practice.test1.entities.UserRole;
import com.practice.test1.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.User;
import com.practice.test1.repositories.UserRepository;
import com.practice.test1.services.UserService;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepository;
	private final UserRoleService userRoleService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		if(!userRepository.existsById(user.getId())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}else {
			throw new DuplicateKeyException("Could not save user. User with same ID already exists.");
		}
	}

	@Override
	public User addRoleToUser(String username, String name) {
		log.info("Adding role '{}' to user '{}'", name, username);
		User user = findByUsername(username);
		UserRole role = userRoleService.findRoleByName(name);
		user.getUserRoles().add(role);
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow( () ->
				new NoSuchElementException(String.format("User with username %s does not exist.", username))
		);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("User not found: %d", id)));
	}

	@Override
	public User updateUser(User user, Long id) {
		User existing = getUserById(id);
		existing.setName(user.getName());

		userRepository.save(existing);

		return existing;
	}

	@Override
	public void deleteUser(Long id) {
		User userById = getUserById(id);
		userRepository.deleteById(userById.getId());
	}
}
