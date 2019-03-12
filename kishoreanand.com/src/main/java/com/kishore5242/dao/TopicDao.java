package com.kishore5242.dao;

import java.util.List;

import com.kishore5242.tests.bean.Topic;

public interface TopicDao {

	List<Topic> getAllTopics();
	
	List<Topic> getAllTopicsByStream(int stream_id);
	
	void removeTopic(int id);

	Topic getTopic(int id);
	
	void updateTopic(Topic topic, Integer stream_id);

	void addTopic(String topicName, Integer pos, Integer stream_id);

	void deleteTopic(Integer topic_id);

}
