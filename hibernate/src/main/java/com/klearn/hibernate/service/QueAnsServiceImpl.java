package com.klearn.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klearn.hibernate.dao.QueAnsDao;
import com.klearn.hibernate.model.Question;

@Service("queAnsService")
@Transactional
public class QueAnsServiceImpl implements QueAnsService{

	@Autowired
	QueAnsDao queAnsDao;
	
	@Override
	public void saveQuestion(Question question) {
		
		queAnsDao.saveQuestion(question);
	}
}
