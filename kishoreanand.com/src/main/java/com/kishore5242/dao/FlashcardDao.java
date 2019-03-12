package com.kishore5242.dao;

import java.util.List;

import com.kishore5242.tests.bean.Flashcard;

public interface FlashcardDao {

	void addFlashcard(Flashcard flashcard, Integer topic_id);

	List<Flashcard> getAllFlashcards();
	
	void removeFlashcard(int id);

	Flashcard getFlashcard(int id);
	
	void updateFlashcard(Flashcard flashcard, Integer topic_id);

	List<Flashcard> getAllFlashcardsByTopicId(Integer topic_id);

}
