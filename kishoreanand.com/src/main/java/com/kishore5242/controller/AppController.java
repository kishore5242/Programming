package com.kishore5242.controller;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kishore5242.service.userService;
import com.kishore5242.tests.bean.User;

@Controller
public class AppController {
	
	private static final Logger logger = LogManager.getLogger(AppController.class);

	@Autowired
	userService userService;
	
	@RequestMapping("/about")
	public String getAbout(HttpServletRequest request, HttpServletResponse response) {

		return "about/about";
	}
	
	@RequestMapping("/admin")
	public String getAdmin(HttpServletRequest request, HttpServletResponse response) {

		return "admin/admin";
	}
	
	@RequestMapping("/user")
	public String getUser(HttpServletRequest request, HttpServletResponse response) {

		return "user/user";
	}
	
	@RequestMapping("/login")
	public String getLogin(HttpServletRequest request, HttpServletResponse response) {

		return "security/login";
	}
	
	@RequestMapping("/register")
	public String getRegister(HttpServletRequest request, HttpServletResponse response) {

		return "security/register";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirectTo = "/register";
		
		boolean userExists = userService.userExists(username);
		
		if(userExists) {
			redirectTo = "/register?userExists";
			response.sendRedirect(redirectTo);
			return;
		}
		
		Set<String> roles = new HashSet<>();
		roles.add("USER");
		
		User user = new User(username, password, true);
		
		userService.saveUser(user, roles);

		redirectTo = "/login?registered";
		
		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping("/403")
	public String get403(HttpServletRequest request, HttpServletResponse response) {

		return "security/403";
	}
	
}
