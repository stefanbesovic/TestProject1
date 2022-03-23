package com.practice.test1.services.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import com.practice.test1.entities.UserRole;
import com.practice.test1.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.User;
import com.practice.test1.repositories.UserRepository;
import com.practice.test1.services.UserService;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
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
	public UserRole saveRole(UserRole role) {
		log.info("Saving role: id={} & name={}", role.getId(), role.getName());
		return userRoleRepository.save(role);
	}

	@Override
	public User addRoleToUser(String username, String name) {
		log.info("Adding role '{}' to user '{}'", name, username);
		User user = userRepository.findByUsername(username);
		UserRole role = userRoleRepository.findByName(name);
		user.getUserRoles().add(role);
		return userRepository.save(user);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null)
			return null;
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getUserRoles().forEach(userRole -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
}
