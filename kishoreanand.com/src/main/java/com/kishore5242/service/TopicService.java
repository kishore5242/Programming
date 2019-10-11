package com.kishore5242.service;

import java.util.List;

import com.kishore5242.bean.Topic;

public interface TopicService {
	
	public List<Topic> getAllTopics();
	
	public List<Topic> getAllTopicsByStream(int stream_id);
	
	public void removeTopic(int id);

	Topic getTopic(int id);

	void createTopics(String topicName, Integer pos, int stream_id);

	void updateTopic(Topic topic, Integer stream_id);

	public void deleteTopic(Integer topic_id);

	
}
