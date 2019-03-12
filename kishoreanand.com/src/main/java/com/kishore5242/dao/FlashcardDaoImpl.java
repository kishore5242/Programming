package com.kishore5242.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.tests.bean.Flashcard;
import com.kishore5242.tests.bean.Topic;
import com.kishore5242.util.SecurityUtil;

@Repository
public class FlashcardDaoImpl implements FlashcardDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addFlashcard(Flashcard flashcard, Integer topic_id) {
		Session session = sessionFactory.getCurrentSession();
		Topic topic = session.get(Topic.class, topic_id);
		flashcard.setTopic(topic);
		
		SecurityUtil.authenticateUser(topic.getStream().getUsername());
		
        session.save(flashcard);
		
	}
	
	@Override
	public Flashcard getFlashcard(int id) {
		Session session = sessionFactory.getCurrentSession();
		Flashcard flashcard = session.get(Flashcard.class, id);
		SecurityUtil.authenticateUser(flashcard.getTopic().getStream().getUsername());
		
		return flashcard;
	}

	@Override
	public List<Flashcard> getAllFlashcards() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Flashcard> list= session.createCriteria(Flashcard.class).list();
        
        if(list.size()>0){
        	SecurityUtil.authenticateUser(list.get(0).getTopic().getStream().getUsername());
        }
        
        return list;
	}

	@Override
	public void removeFlashcard(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Flashcard p = (Flashcard) session.load(Flashcard.class, new Integer(id));
		SecurityUtil.authenticateUser(p.getTopic().getStream().getUsername());
		if(null != p){
			session.delete(p);
		}
	}

	@Override
	public void updateFlashcard(Flashcard flashcard, Integer topic_id) {
		Session session = sessionFactory.getCurrentSession();
		
		Flashcard fc = session.get(Flashcard.class, flashcard.getFlashcard_id());
		Topic topic = session.get(Topic.class, topic_id);
		
		fc.setFront(flashcard.getFront());
		fc.setBack(flashcard.getBack());
		fc.setPosition(flashcard.getPosition());
		fc.setTopic(topic);
		fc.setColor(flashcard.getColor());
		SecurityUtil.authenticateUser(fc.getTopic().getStream().getUsername());
		session.update(fc);
		
	}

	@Override
	public List<Flashcard> getAllFlashcardsByTopicId(Integer topic_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Flashcard where topic_id= :id");
		
		query.setLong("id", topic_id);
		List<Flashcard> flashcards = query.list();
        if(flashcards.size()>0){
        	SecurityUtil.authenticateUser(flashcards.get(0).getTopic().getStream().getUsername());
        }
		return flashcards;
	}

}
