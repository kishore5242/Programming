package com.kishore5242;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kishore5242.service.FlashcardService;
import com.kishore5242.service.FlashcardServiceImpl;
import com.kishore5242.tests.bean.Flashcard;

@Controller
public class AppController {
	
	private static final Logger logger = LogManager.getLogger(AppController.class);
	
	@Autowired
	FlashcardService flashcardService;
	
	List<Flashcard> allFlashcards = new ArrayList<>();
	

	@RequestMapping("/tests")
	public String getTest(HttpServletRequest request, HttpServletResponse response) {

		return "tests/test";
	}
	
	@RequestMapping("/cards")
	public String getLearn(HttpServletRequest request, HttpServletResponse response) {

		return "cards/cardsHome";
	}
	
	@RequestMapping("/cards/java")
	public String getLearnJava(HttpServletRequest request, HttpServletResponse response) {

		if(this.allFlashcards != null && this.allFlashcards.size() > 0){
		} else {
			this.allFlashcards = flashcardService.getAllFlashcards();
		}
		request.setAttribute("allFlashcards", allFlashcards);
		
		return "cards/java";
	}
	
	@RequestMapping("/cards/javascript")
	public String getLearnJavascript(HttpServletRequest request, HttpServletResponse response) {

		if(this.allFlashcards != null && this.allFlashcards.size() > 0){
		} else {
			this.allFlashcards = flashcardService.getAllFlashcards();
		}
		request.setAttribute("allFlashcards", allFlashcards);
		
		return "cards/javascript";
	}
	
	@RequestMapping("/about")
	public String getAbout(HttpServletRequest request, HttpServletResponse response) {

		return "about/about";
	}
	
	@RequestMapping("/addFlashcard")
	public String addFlashcard(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("inside /addFlashcard...");
		
		return "admin/addCard";
	}
	
	@RequestMapping(value = "/saveFlashcard", method = RequestMethod.POST)
	public void saveFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String redirectTo = "/cards";
		
		String stream = request.getParameter("stream");
		String front = request.getParameter("front");
		String back = request.getParameter("back");
		String position = request.getParameter("position");
		int pos = 0;
		
		Flashcard flashcard = new Flashcard();
		flashcard.setStream(stream);
		flashcard.setFront(front);
		flashcard.setBack(back);
		
		if(null != position && !position.isEmpty()) {
			pos = Integer.parseInt(position);
		}
		
		flashcard.setPosition(pos);
		
		flashcardService.createFlashcard(flashcard);
		/*
		this.allFlashcards = flashcardService.getAllFlashcards();
		request.setAttribute("allFlashcards", allFlashcards);
		*/
		if("Java".equalsIgnoreCase(stream)){
			redirectTo = "/cards/java";
		} else if("JavaScript".equalsIgnoreCase(stream)) {
			redirectTo = "/cards/javascript";
		}
		// reload cards
		this.allFlashcards = null;
		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping(value = "/deleteFlashcard", method = RequestMethod.POST)
	public void deleteFlashcard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String redirectTo = "/cards";
		
		String stream = request.getParameter("deleteStream");
		String idStr = request.getParameter("deleteId");
		
		int id = -1;
		if(null != idStr && !idStr.isEmpty()) {
			id = Integer.parseInt(idStr);
		}
		
		flashcardService.removeFlashcard(id);

		this.allFlashcards = flashcardService.getAllFlashcards();
		request.setAttribute("allFlashcards", allFlashcards);
		
		if("Java".equalsIgnoreCase(stream)){
			redirectTo = "/cards/java";
		} else if("JavaScript".equalsIgnoreCase(stream)) {
			redirectTo = "/cards/javascript";
		}
		// reload cards
		this.allFlashcards = null;
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
		
		String redirectTo = "/cards";
		
		String cardId = request.getParameter("cardId");
		String stream = request.getParameter("stream");
		String front = request.getParameter("front");
		String back = request.getParameter("back");
		String position = request.getParameter("position");
		int pos = 0;
		int id = 0;
		
		Flashcard flashcard = new Flashcard();
		flashcard.setStream(stream);
		flashcard.setFront(front);
		flashcard.setBack(back);
		
		if(null != position && !position.isEmpty()) {
			pos = Integer.parseInt(position);
		}
		if(null != cardId && !cardId.isEmpty()) {
			id = Integer.parseInt(cardId);
		}
		
		flashcard.setId(id);
		flashcard.setPosition(pos);
		
		flashcardService.updateFlashcard(flashcard);
		/*
		this.allFlashcards = flashcardService.getAllFlashcards();
		request.setAttribute("allFlashcards", allFlashcards);
		*/
		if("Java".equalsIgnoreCase(stream)){
			redirectTo = "/cards/java";
		} else if("JavaScript".equalsIgnoreCase(stream)) {
			redirectTo = "/cards/javascript";
		}
		// reload cards
		this.allFlashcards = null;
		response.sendRedirect(redirectTo);
	}
}
