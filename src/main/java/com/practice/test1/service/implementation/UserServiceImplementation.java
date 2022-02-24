package com.practice.test1.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.domen.User;
import com.practice.test1.repository.UserRepository;
import com.practice.test1.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

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
