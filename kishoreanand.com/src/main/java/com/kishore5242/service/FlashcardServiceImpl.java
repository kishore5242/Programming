package com.kishore5242.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.dao.FlashcardDao;
import com.kishore5242.tests.bean.Flashcard;

@Service
@Transactional
public class FlashcardServiceImpl implements FlashcardService {

	@Autowired
	FlashcardDao flashcardDao;
	
	@Override
	public void createFlashcard(Flashcard flashcard) {
		flashcardDao.addFlashcard(flashcard);
		
	}
	
	@Override
	public Flashcard getFlashcard(int id) {
		return flashcardDao.getFlashcard(id); 
	}

	@Override
	public List<Flashcard> getAllFlashcards() {
		
		List<Flashcard> allCards = flashcardDao.getAllFlashcards();
		
		Collections.sort(allCards);
		
		return allCards;
	}

	@Override
	public void removeFlashcard(int id) {
		flashcardDao.removeFlashcard(id);
	}
	
	@Override
	public void updateFlashcard(Flashcard flashcard) {
		flashcardDao.updateFlashcard(flashcard);
	}

}
