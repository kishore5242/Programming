package com.kishore5242.service;

import java.util.Set;

import com.kishore5242.tests.bean.User;

public interface userService {

	boolean userExists(String username);

	void saveUser(User user, Set<String> roles);

	
}
