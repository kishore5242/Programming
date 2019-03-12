package com.kishore5242.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.tests.bean.Stream;
import com.kishore5242.tests.bean.Topic;
import com.kishore5242.util.SecurityUtil;

@Repository
public class TopicDaoImpl implements TopicDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addTopic(String topicName, Integer pos, Integer stream_id) {
		Session session = sessionFactory.getCurrentSession();
		Stream stream = session.get(Stream.class, stream_id);
		Topic topic = new Topic();
		topic.setTopic_name(topicName);
		topic.setPos(pos);
		topic.setStream(stream);
		SecurityUtil.authenticateUser(stream.getUsername());
        session.save(topic);
	}

	@Override
	public List<Topic> getAllTopics() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Topic> list= session.createCriteria(Topic.class).list();
		if(list.size()>0){
			SecurityUtil.authenticateUser(list.get(0).getStream().getUsername());
		}
        return list;
	}

	@Override
	public List<Topic> getAllTopicsByStream(int stream_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Topic where stream_id= :id");
		
		query.setLong("id", stream_id);
		List<Topic> topics = query.list();
		
		if(topics.size()>0){
			SecurityUtil.authenticateUser(topics.get(0).getStream().getUsername());
		}

		return topics;
	}

	@Override
	public void removeTopic(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Topic p = (Topic) session.load(Topic.class, new Integer(id));
		SecurityUtil.authenticateUser(p.getStream().getUsername());
		if(null != p){
			session.delete(p);
		}
		
	}

	@Override
	public Topic getTopic(int id) {
		Session session = sessionFactory.getCurrentSession();
		Topic topic = session.get(Topic.class, id);
		SecurityUtil.authenticateUser(topic.getStream().getUsername());
		return topic;
	}

	@Override
	public void updateTopic(Topic topic, Integer stream_id) {
		
		Session session = sessionFactory.getCurrentSession();
		Topic t = session.get(Topic.class, topic.getTopic_id());
		Stream s = session.get(Stream.class, stream_id);
		SecurityUtil.authenticateUser(s.getUsername());
		t.setTopic_name(topic.getTopic_name());
		t.setPos(topic.getPos());
		t.setStream(s);

		session.update(t);
		
	}

	@Override
	public void deleteTopic(Integer topic_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Topic t = (Topic) session.load(Topic.class, topic_id);
		SecurityUtil.authenticateUser(t.getStream().getUsername());
		if(null != t){
			session.delete(t);
		}
	}
	
}
