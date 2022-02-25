package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.User;
import com.practice.test1.repositories.UserRepository;
import com.practice.test1.services.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		if(!userRepository.existsById(user.getId())) {
			return userRepository.save(user);
		}else {
			throw new DuplicateKeyException("Could not save user. User with same ID already exists.");
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("User not found: %d", id)));
	}

	@Override
	public User updateUser(User user, long id) {
		User existing = getUserById(id);
		existing.setName(user.getName());
		userRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteUser(long id) {
		getUserById(id);
		userRepository.deleteById(id);
	}
}
