package com.kishore5242.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kishore5242.bean.Stream;
import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.service.BlogService;
import com.kishore5242.service.StreamService;
import com.kishore5242.util.SecurityUtil;

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
		
		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		List<Stream> streams = streamService.getAllStreamsByUser(loggedInUser);
		request.getSession().setAttribute("streams", streams);
		request.setAttribute("streams", streams);	
		
		List<Blog> blogs = blogService.getAllBlogs();
		request.getSession().setAttribute("blogs", blogs);
		request.setAttribute("blogs", blogs);
		
		return "welcome";
	}

}