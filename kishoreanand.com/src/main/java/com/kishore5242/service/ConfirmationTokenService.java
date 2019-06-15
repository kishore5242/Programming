package com.kishore5242.service;

import com.kishore5242.tests.bean.ConfirmationToken;

public interface ConfirmationTokenService {
	
	ConfirmationToken findTokenById(long tokenid);
	
	ConfirmationToken findToken(String token);
	
	void saveToken(ConfirmationToken confirmationToken);
}
