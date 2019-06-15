package com.kishore5242.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kishore5242.service.FlashcardService;
import com.kishore5242.service.TopicService;
import com.kishore5242.tests.bean.Flashcard;
import com.kishore5242.tests.bean.Topic;

@Controller
public class AppCardController {
	
	private static final Logger logger = LogManager.getLogger(AppCardController.class);
	
	@Autowired
	FlashcardService flashcardService;
	
	@Autowired
	TopicService topicService;
	
	List<Flashcard> allFlashcards = new ArrayList<>();
	
	@RequestMapping("/cards")
	public String getCards(HttpServletRequest request, HttpServletResponse response) {
			
		String topic_id_str = request.getParameter("topic_id");
		Integer topic_id = Integer.parseInt(topic_id_str);
		
		Topic topic = topicService.getTopic(topic_id);
		
		List<Flashcard> flashcards = flashcardService.getAllFlashcardsByTopicId(topic_id);
		
		request.setAttribute("flashcards", flashcards);
		request.setAttribute("topic", topic);

		return "cards/simpleCard";
	}
	
	@RequestMapping("/addFlashcard")
	public String addFlashcard(HttpServletRequest request, HttpServletResponse response) {
		
		String topic_id_str = request.getParameter("topic_id");
		Integer topic_id = Integer.parseInt(topic_id_str);
		
		Topic topic = topicService.getTopic(topic_id);
		
		request.setAttribute("topic", topic);
				
		return "admin/addCard";
	}
	
	@RequestMapping(value = "/saveFlashcard", method = RequestMethod.POST)
	public void saveFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String topic_id_str = request.getParameter("topic_id");
		Integer topic_id = Integer.parseInt(topic_id_str);
		
		String redirectTo = "/cards?topic_id="+topic_id;
		
		String stream = request.getParameter("stream");
		String front = request.getParameter("front");
		String back = request.getParameter("back");
		String position = request.getParameter("position");
		String color = request.getParameter("selectedColor");
		int pos = 0;
		
		Flashcard flashcard = new Flashcard();
		flashcard.setStream(stream);
		flashcard.setFront(front);
		flashcard.setBack(back);
		flashcard.setColor(color);
		
		if(null != position && !position.isEmpty()) {
			pos = Integer.parseInt(position);
		}
		
		flashcard.setPosition(pos);
		
		flashcardService.createFlashcard(flashcard, topic_id);

		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping(value = "/deleteFlashcard", method = RequestMethod.POST)
	public void deleteFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String topic_id_str = request.getParameter("topic_id");
		//Integer topic_id = Integer.parseInt(topic_id_str);
		
		String redirectTo = "/cards?topic_id="+topic_id_str;
		
		String stream = request.getParameter("deleteStream");
		String idStr = request.getParameter("deleteId");
		
		int id = -1;
		if(null != idStr && !idStr.isEmpty()) {
			id = Integer.parseInt(idStr);
		}
		
		flashcardService.removeFlashcard(id);

		response.sendRedirect(redirectTo);
	}
	
	@RequestMapping(value = "/editFlashcard")
	public String editFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String cardIdStr = request.getParameter("cardId");
		int cardId = 555;
		try {
			cardId = Integer.parseInt(cardIdStr);
			
		} catch (NumberFormatException e) {
			logger.error(e);
			return "error/error";
		}
						
		Flashcard flashcard = flashcardService.getFlashcard(cardId);

		request.setAttribute("flashcard", flashcard);
		
		return "admin/editCard";
	}
	
	
	@RequestMapping(value = "/updateFlashcard", method = RequestMethod.POST)
	public void updateFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String topic_id_str = request.getParameter("topic_id");
		Integer topic_id = Integer.parseInt(topic_id_str);
		
		String redirectTo = "/cards?topic_id="+topic_id_str;
		
		String cardId = request.getParameter("cardId");
		String stream = request.getParameter("stream");
		String front = request.getParameter("front");
		String back = request.getParameter("back");
		String position = request.getParameter("position");
		String color = request.getParameter("selectedColor");
		int pos = 0;
		int id = 0;
		
		Flashcard flashcard = new Flashcard();
		flashcard.setStream(stream);
		flashcard.setFront(front);
		flashcard.setBack(back);
		flashcard.setColor(color);
		
		if(null != position && !position.isEmpty()) {
			pos = Integer.parseInt(position);
		}
		if(null != cardId && !cardId.isEmpty()) {
			id = Integer.parseInt(cardId);
		}
		
		flashcard.setFlashcard_id(id);
		flashcard.setPosition(pos);
		
		flashcardService.updateFlashcard(flashcard, topic_id);

		response.sendRedirect(redirectTo);
	}
	
	
	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		HashMap<String, String[]> hm = new HashMap<String, String[]>();
		hm.putAll(request.getParameterMap());
		List<String> orderArray = Arrays.asList(hm.get("orderArray[]"));
		
		String returnStr = flashcardService.saveOrder(orderArray);
		
		return returnStr;
	}
}
