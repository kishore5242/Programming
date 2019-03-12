package com.kishore5242.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.dao.UserDao;

@Service
@Transactional
public class userServiceImpl implements userService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//get user from the database, via Hibernate
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean userExists(String username) {
		com.kishore5242.tests.bean.User user = userDao.findByUserName(username);
		
		if(user != null && user.getUsername().equalsIgnoreCase(username)){
			return true;
		}
		
		return false;
	}
	
	@Override
    public void saveUser(com.kishore5242.tests.bean.User user, Set<String> roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userDao.saveUser(user, roles);
    }

}
