package org.springframework.samples.xtreme.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	@Transactional
	public void updateUser(User user) throws DataAccessException {
		userRepository.save(user);
	}
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findById(username);
	}
}
