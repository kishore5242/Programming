package com.kishore5242.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.bean.UserRole;
import com.kishore5242.dao.UserDao;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//get user from the database, via Hibernate
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
	
		com.kishore5242.bean.User user = userDao.findByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not registered with us!");
		}
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.kishore5242.bean.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
			user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	public String getLoggedInUsername(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "admin";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} 
		return username;
	}

}