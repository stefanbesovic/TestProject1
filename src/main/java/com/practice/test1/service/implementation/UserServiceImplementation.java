package com.practice.test1.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.User;
import com.practice.test1.repository.UserRepository;
import com.practice.test1.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		if(!userRepository.existsById(user.getId())) {
			return userRepository.save(user);
		}else {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User user, long id) {
		User existing = userRepository.findById(id).orElse(null);
		if(existing.equals(null)) {
			return null;
		}
		existing.setName(user.getName());
		userRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteUser(long id) {
		User exists = userRepository.findById(id).orElse(null);
		if(exists.equals(null)) {
			return;
		}
		userRepository.deleteById(id);
	}
}
