package com.kishore5242.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kishore5242.service.StreamService;
import com.kishore5242.service.TopicService;
import com.kishore5242.tests.bean.Flashcard;
import com.kishore5242.tests.bean.Stream;
import com.kishore5242.tests.bean.Topic;

@Controller
public class AppTopicController {
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	StreamService streamService;
	
	List<Flashcard> allFlashcards = new ArrayList<>();
	
	@RequestMapping("/topics")
	public String getTopics(HttpServletRequest request, HttpServletResponse response) {
		
		String stream_idStr = request.getParameter("stream_id");
		int stream_id = 0;
		
		if(stream_idStr != null && !stream_idStr.isEmpty()){
			stream_id = Integer.parseInt(stream_idStr);
		}
		
		Stream stream =  streamService.getStream(stream_id);
		
		List<Topic> topics = topicService.getAllTopicsByStream(stream_id);

		request.setAttribute("stream", stream);
		request.setAttribute("topics", topics);
		
		return "topics/topics";
	}
	
	@RequestMapping("/addTopic")
	public String addTopic(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("stream_id", request.getParameter("stream_id"));
		
		return "admin/addTopic";
	}
	
	@RequestMapping(value = "/saveTopic", method = RequestMethod.POST)
	public void saveTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String topicName = request.getParameter("topicName");
		String posStr = request.getParameter("pos");
		String stream_idStr = request.getParameter("stream_id");

		String redirectTo = "/topics?stream_id="+stream_idStr;
		
		int stream_id = Integer.parseInt(stream_idStr);
		int pos = Integer.parseInt(posStr);
		
		topicService.createTopics(topicName, pos, stream_id);

		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/editTopic")
	public String editTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String topic_id_str = request.getParameter("topic_id");
		int topic_id = 555;
		try {
			topic_id = Integer.parseInt(topic_id_str);
			
		} catch (NumberFormatException e) {
			return "error/error";
		}
						
		Topic topic = topicService.getTopic(topic_id);

		request.setAttribute("topic", topic);
		
		return "admin/editTopic";
	}
	
	@RequestMapping(value = "/updateTopic", method = RequestMethod.POST)
	public void updateTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String stream_id_str = request.getParameter("stream_id");
		
		String redirectTo = "/topics?stream_id="+stream_id_str;
		
		String topic_id_str = request.getParameter("topic_id");
		String topic_name = request.getParameter("topic_name");
		String position = request.getParameter("pos");

		int pos = 0;
		int stream_id = 0;
		int topic_id =0;
		
		if(null != position && !position.isEmpty()) {
			pos = Integer.parseInt(position);
		}
		if(null != stream_id_str && !stream_id_str.isEmpty()) {
			stream_id = Integer.parseInt(stream_id_str);
		}
		if(null != topic_id_str && !topic_id_str.isEmpty()) {
			topic_id = Integer.parseInt(topic_id_str);
		}
		
		Topic topic = new Topic();
		topic.setTopic_id(topic_id);
		topic.setTopic_name(topic_name);
		topic.setPos(pos);
		
		topicService.updateTopic(topic, stream_id);

		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
	public void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String topic_id_str = request.getParameter("topic_id");
		Integer topic_id = Integer.parseInt(topic_id_str);
		
		String stream_id_str = request.getParameter("stream_id");
		String redirectTo = "/topics?stream_id="+stream_id_str;
		
		topicService.deleteTopic(topic_id);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
}
