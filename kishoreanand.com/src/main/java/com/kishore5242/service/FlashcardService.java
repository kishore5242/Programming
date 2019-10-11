package com.kishore5242.service;

import java.util.List;

import com.kishore5242.bean.Flashcard;

public interface FlashcardService {

	public void createFlashcard(Flashcard flashcard, Integer topic_id);
	
	public List<Flashcard> getAllFlashcards();
	
	public List<Flashcard> getAllFlashcardsByTopicId(Integer topic_id);
	
	public void removeFlashcard(int id);

	Flashcard getFlashcard(int id);

	void updateFlashcard(Flashcard flashcard, Integer topic_id);

	public String saveOrder(List<String> orderArray);
	
}
