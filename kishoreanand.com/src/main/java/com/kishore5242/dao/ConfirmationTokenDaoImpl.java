package com.kishore5242.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.bean.ConfirmationToken;
import com.kishore5242.bean.User;

@Repository
public class ConfirmationTokenDaoImpl implements ConfirmationTokenDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ConfirmationToken findTokenById(long tokenid) {
		Session session = sessionFactory.getCurrentSession();
		ConfirmationToken confirmationToken = session.get(ConfirmationToken.class, tokenid);
		return confirmationToken;
	}

	@Override
	public void saveToken(ConfirmationToken confirmationToken) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, confirmationToken.getUser().getUsername());
		confirmationToken.setUser(user);
		session.save(confirmationToken);
	}

	@Override
	public ConfirmationToken findToken(String token) {
        Session session = sessionFactory.getCurrentSession();
        
        Criteria criteria = session.createCriteria(ConfirmationToken.class)
        		.add(Restrictions.eq("confirmationToken", token))
            	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        
        @SuppressWarnings("unchecked")
        List<ConfirmationToken> list= criteria.list();
        
        if(list.size()>0){
        	return list.get(0);
        } else {
        	return null;
        }
	}

}
