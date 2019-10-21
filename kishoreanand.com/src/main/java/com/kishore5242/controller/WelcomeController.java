package com.kishore5242.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kishore5242.blog.service.BlogService;
import com.kishore5242.service.StreamService;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Autowired
	StreamService streamService;
	
	@Autowired
	BlogService blogService;
	
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		request.setAttribute("message", this.message);
		
		return "welcome";
	}

}