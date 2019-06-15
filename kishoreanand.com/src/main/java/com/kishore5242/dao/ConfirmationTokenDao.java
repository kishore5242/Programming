package com.kishore5242.dao;

import com.kishore5242.tests.bean.ConfirmationToken;

public interface ConfirmationTokenDao {

	ConfirmationToken findTokenById(long tokenid);
	
	void saveToken(ConfirmationToken confirmationToken);

	ConfirmationToken findToken(String token);

}