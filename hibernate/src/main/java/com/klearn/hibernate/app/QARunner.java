package com.klearn.hibernate.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.klearn.hibernate.config.AppConfig;
import com.klearn.hibernate.model.Answer;
import com.klearn.hibernate.model.Question;
import com.klearn.hibernate.service.QueAnsService;

public class QARunner {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 
		QueAnsService service = (QueAnsService) context.getBean("queAnsService");

        Question question = new Question();
        question.setqDesc("What is Java?");
        
        Answer answer1 = new Answer();
        answer1.setaDesc("It is a programming language.");
        
        Answer answer2 = new Answer();
        answer2.setaDesc("It is an Object Oriented Programming language..");
        
        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        
        service.saveQuestion(question);
	}

}
