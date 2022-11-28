package org.springframework.samples.xtreme.user;

import java.util.Optional;

import javax.persistence.EntityManager;

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
    public void save(User u){
        this.userRepository.save(u);
    }


	@Transactional
	public Optional<User> findByUsername(String username) {
		return userRepository.findById(username);
	}

	@Transactional
    public void remove(User u){
        this.userRepository.delete(u); 
    }
	
	@Transactional
    public void removeById(String userId){
        this.userRepository.deleteById(userId);
    }
}
