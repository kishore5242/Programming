package com.kishore5242.service;

import java.util.Set;

import com.kishore5242.bean.User;

public interface userService {

	boolean userExists(String username);

	void saveUser(User user, Set<String> roles);
	
	void updateUser(User user);

	User findUserByUsername(String username);

	void updateUserPassword(User user);
	
}
