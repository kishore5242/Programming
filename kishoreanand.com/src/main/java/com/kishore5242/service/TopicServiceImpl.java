package com.kishore5242.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.dao.StreamDao;
import com.kishore5242.dao.TopicDao;
import com.kishore5242.tests.bean.Topic;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	TopicDao topicDao;
	
	@Autowired
	StreamDao streamDao;

/*	@Override
	public void createTopic(Topic topic) {
		topicDao.addTopic(topic);
	}*/

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> allTopics = topicDao.getAllTopics();
		Collections.sort(allTopics);
		return allTopics;
	}
	
	@Override
	public List<Topic> getAllTopicsByStream(int stream_id) {
		List<Topic> allTopics = topicDao.getAllTopicsByStream(stream_id);
		Collections.sort(allTopics);
		return allTopics;
	}

	@Override
	public void removeTopic(int id) {
		topicDao.removeTopic(id);
	}

	@Override
	public Topic getTopic(int id) {
		return topicDao.getTopic(id);
	}

	@Override
	public void updateTopic(Topic topic, Integer stream_id) {
		topicDao.updateTopic(topic, stream_id);
	}

	@Override
	public void createTopics(String topicName, Integer pos, int stream_id) {
				
		topicDao.addTopic(topicName, pos, stream_id);

	}

	@Override
	public void deleteTopic(Integer topic_id) {
		topicDao.deleteTopic(topic_id);
		
	}


}
