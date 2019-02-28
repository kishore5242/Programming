package com.kishore5242.service;

import java.util.List;

import com.kishore5242.tests.bean.Flashcard;

public interface FlashcardService {

	public void createFlashcard(Flashcard flashcard);
	
	public List<Flashcard> getAllFlashcards();
	
	public void removeFlashcard(int id);

	Flashcard getFlashcard(int id);

	void updateFlashcard(Flashcard flashcard);
}
