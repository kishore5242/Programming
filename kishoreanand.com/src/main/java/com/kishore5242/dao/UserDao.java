package com.kishore5242.dao;

import java.util.Set;

import com.kishore5242.bean.User;

public interface UserDao {

	User findByUserName(String username);
	
	void saveUser(User user, Set<String> roles);

	void updateUser(User user);

}