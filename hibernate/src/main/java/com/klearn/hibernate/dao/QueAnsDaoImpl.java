package com.klearn.hibernate.dao;

import org.springframework.stereotype.Repository;

import com.klearn.hibernate.model.Question;

@Repository
public class QueAnsDaoImpl extends AbstractDao implements QueAnsDao {
	
	@Override
	public void saveQuestion(Question question) {
		
		persist(question);
		
	}

}
