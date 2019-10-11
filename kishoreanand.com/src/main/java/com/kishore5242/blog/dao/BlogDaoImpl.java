package com.kishore5242.blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.blog.entity.Blog;
import com.kishore5242.util.SecurityUtil;

@Repository
public class BlogDaoImpl implements BlogDao {

	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Blog> getAllBlogs() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Blog> list= session.createCriteria(Blog.class)
        	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
        	.list();
        return list;
	}

	@Override
	public void addBlog(Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		SecurityUtil.authenticateUser(blog.getUsername());
		session.save(blog);
	}

	@Override
	public void removeBlog(Integer blog_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Blog b = (Blog) session.load(Blog.class, new Integer(blog_id));
		SecurityUtil.authenticateUser(b.getUsername());
		if(null != b){
			session.delete(b);
		}
	}

	@Override
	public Blog getBlog(Integer blog_id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, blog_id);
		SecurityUtil.authenticateUser(blog.getUsername());
		return blog;
	}

	@Override
	public void updateBlog(Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		Blog b = session.get(Blog.class, blog.getBlog_id());
		b.setBlog_name(blog.getBlog_name());
		SecurityUtil.authenticateUser(b.getUsername());
		session.update(b);
	}


	@Override
	public void deleteBlog(Integer blog_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Blog b = (Blog) session.load(Blog.class, blog_id);
		SecurityUtil.authenticateUser(b.getUsername());
		if(null != b){
			session.delete(b);
		}
	}

}
