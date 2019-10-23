package com.kishore5242.blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.blog.entity.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Comment save(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(comment);
		return session.get(Comment.class, id);
	}

	@Override
	public List<Comment> findAll() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Comment> comments = session.createCriteria(Comment.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
		return comments;
	}

	@Override
	public Comment findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Comment.class, id);
	}

	@Override
	public List<Comment> findAllByPostId(Integer post_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Comment where post_id=:i");
		query.setParameter("i", post_id);
		List<Comment> list = query.list();
		return list;
	}

	
	
}
