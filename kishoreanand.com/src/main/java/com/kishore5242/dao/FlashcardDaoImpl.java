package com.kishore5242.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.tests.bean.Flashcard;

@Repository
public class FlashcardDaoImpl implements FlashcardDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addFlashcard(Flashcard flashcard) {
		Session session = sessionFactory.getCurrentSession();
        session.save(flashcard);
		
	}
	
	@Override
	public Flashcard getFlashcard(int id) {
		Session session = sessionFactory.getCurrentSession();
		Flashcard flashcard = session.get(Flashcard.class, id);
		return flashcard;
	}

	@Override
	public List<Flashcard> getAllFlashcards() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Flashcard> list= session.createCriteria(Flashcard.class).list();
        return list;
	}

	@Override
	public void removeFlashcard(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Flashcard p = (Flashcard) session.load(Flashcard.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
	}

	@Override
	public void updateFlashcard(Flashcard flashcard) {
		Session session = sessionFactory.getCurrentSession();
		session.update(flashcard);
		
	}

}
