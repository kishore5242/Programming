package com.kishore5242.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.bean.ConfirmationToken;
import com.kishore5242.dao.ConfirmationTokenDao;

@Service("confirmationTokenService")
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

	@Autowired
	ConfirmationTokenDao confirmationTokenDao;
	
	@Override
	public ConfirmationToken findTokenById(long tokenid) {
		return confirmationTokenDao.findTokenById(tokenid);
	}

	@Override
	public void saveToken(ConfirmationToken confirmationToken) {
		confirmationTokenDao.saveToken(confirmationToken);
	}

	@Override
	public ConfirmationToken findToken(String token) {
		return confirmationTokenDao.findToken(token);
	}

}
