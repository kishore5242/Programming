package com.kishore5242.controller;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kishore5242.service.ConfirmationTokenService;
import com.kishore5242.service.EmailService;
import com.kishore5242.service.userService;
import com.kishore5242.tests.bean.ConfirmationToken;
import com.kishore5242.tests.bean.User;

@Controller
public class AppController {
	
	private static final Logger logger = LogManager.getLogger(AppController.class);

	@Autowired
	userService userService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ConfirmationTokenService confirmationTokenService;
	
	
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
	
	@RequestMapping("/reset-page")
	public String getReste(HttpServletRequest request, HttpServletResponse response) {

		return "security/reset";
	}
	
	@RequestMapping(value = "/reset-email", method = RequestMethod.POST)
	public void resetEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("username");
		
		String redirectTo = "/reset-page";
		
		User user = userService.findUserByUsername(username);
		
		if(user == null) {
			redirectTo = "/reset-page?noUsername";
			response.sendRedirect(redirectTo);
			return;
		} else if(user != null && !user.isEnabled()){
			redirectTo = "/reset-page?userNotEnabled";
			response.sendRedirect(redirectTo);
			return;
		}
		
		request.setAttribute("username", username);
		
		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenService.saveToken(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Reset Password Link!");
        mailMessage.setFrom("kishore5242");
        mailMessage.setText("To reset your password, please click here : "
        +"http://kishoreanand.com/new-password?token="+confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
		
		redirectTo = "/account?resetEmailSent";
		
		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping(value = "/new-password")
	public String newPasswordPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String tokenStr = request.getParameter("token");
		
		request.setAttribute("token", tokenStr);
		
		return "security/newPassword";
		
	}
	
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String tokenStr = request.getParameter("token");
		String password = request.getParameter("password");
		
		String redirectTo = "/new-password";
		
		if(tokenStr != null && !tokenStr.isEmpty()){
			
			ConfirmationToken token = confirmationTokenService.findToken(tokenStr);
			
			if(token != null)
	        {
	            User user = userService.findUserByUsername(token.getUser().getUsername());
	            user.setPassword(password);
	            userService.updateUserPassword(user);
	            redirectTo = "/account?passwordReset";
	            
	        } else {
	        	redirectTo = "/new-password?invalidToken";
	        }
			
		} else {
			redirectTo = "/new-password?invalidToken";
		}

		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("username");
		String displayName = request.getParameter("displayName");
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
		
		User user = new User(username, displayName, password, false);
		
		userService.saveUser(user, roles);

		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenService.saveToken(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("kishore5242");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://kishoreanand.com/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
		
		redirectTo = "/account?registered";
		
		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping(value = "/confirm-account")
	public void confirmUserAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String tokenStr = request.getParameter("token");
		
		String redirectTo = "/account";
		
		if(tokenStr != null && !tokenStr.isEmpty()){
			
			ConfirmationToken token = confirmationTokenService.findToken(tokenStr);
			
			if(token != null)
	        {
	            User user = userService.findUserByUsername(token.getUser().getUsername());
	            user.setEnabled(true);
	            userService.updateUser(user);
	            
	        } else {
	        	redirectTo = "/account?invalidToken";
	        }
			
		} else {
			redirectTo = "/account?invalidToken";
		}
		
		redirectTo = "/account?confirmed";
		
		response.sendRedirect(redirectTo);
		
	}
	
	@RequestMapping("/account")
	public String createAccount(HttpServletRequest request, HttpServletResponse response) {

		return "security/account";
	}
	
	@RequestMapping("/403")
	public String get403(HttpServletRequest request, HttpServletResponse response) {

		return "security/403";
	}
	
}
