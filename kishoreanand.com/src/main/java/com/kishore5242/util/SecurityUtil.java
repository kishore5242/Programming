package com.kishore5242.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
	
	private static final Logger logger = LogManager.getLogger(SecurityUtil.class);

	public static String getLoggedInUserName(Authentication authentication) {
		String loggedInUser = "kishore5242@gmail.com";
		
		if(authentication != null){
			loggedInUser = authentication.getName();
		}
				
		return loggedInUser;
	}
	
	public static String getLoggedInUserName(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "kishore5242@gmail.com";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		return username;
	}
	
	public static boolean authenticateUser(String username){
		String loggedInUser = getLoggedInUserName();
		if(null != loggedInUser
				&& null != username
				&& loggedInUser.equalsIgnoreCase(username)){
			return true;
		} else {
			logger.warn(loggedInUser + " is not authorized to perform this action!");
			throw new AccessDeniedException(loggedInUser + " is not authorized to perform this action!");
		}
	}
	
	public static boolean checkLogIn() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof UserDetails)) {
			logger.warn("User has not logged in!");
			return false;
		} else {
			return true;
		}

	}
	
}
