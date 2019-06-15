package com.kishore5242.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.tests.bean.User;
import com.kishore5242.tests.bean.UserRole;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void saveUser(User user, Set<String> userRoles) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
		
		for(String userRoleStr: userRoles){
			UserRole userRole = new UserRole(user, userRoleStr);
			user.getUserRole().add(userRole);
			session.save(userRole);
		}

	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		User u = session.get(User.class, user.getUsername());
		u.setDisplayName(user.getDisplayName());
		u.setPassword(user.getPassword());
		u.setEnabled(user.isEnabled());
		u.setUserRole(user.getUserRole());
		session.update(u);
	}

}