package com.kishore5242.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.util.SecurityUtil;

@Repository
public class PostDaoImpl implements PostDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Integer addPost(Post post, Integer blog_id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, blog_id);
		
		Post p = new Post();
		p.setPost_name(post.getPost_name());
		p.setPost_author(post.getPost_author());
		p.setPost_html_path(post.getPost_html_path());
		p.setUsername(post.getUsername());
		p.setPosition(post.getPosition());
		p.setPost_modified(post.getPost_modified());
		p.setBlog(blog);
		
		//SecurityUtil.authenticateUser(post.getUsername());
		SecurityUtil.checkIfBlogAdmin();
		
        return (Integer) session.save(p);
	}

	@Override
	public List<Post> getAllPosts() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Post> list= session.createCriteria(Post.class).list();
        return list;
	}

	@Override
	public List<Post> getAllPostsByBlog(Integer blog_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post where blog_id= :id");
		
		query.setLong("id", blog_id);
		List<Post> posts = query.list();

		return posts;
	}

	@Override
	public void removePost(Integer post_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Post p = (Post) session.load(Post.class, new Integer(post_id));
		//SecurityUtil.authenticateUser(p.getUsername());
		SecurityUtil.checkIfBlogAdmin();
		
		if(null != p){
			session.delete(p);
		}
		
	}

	@Override
	public Post getPost(Integer post_id) {
		Session session = sessionFactory.getCurrentSession();
		Post post = session.get(Post.class, post_id);
		//SecurityUtil.authenticateUser(post.getUsername());
		//SecurityUtil.checkIfBlogAdmin();
		return post;
	}

	@Override
	public void updatePost(Post post, Integer blog_id) {
		
		Session session = sessionFactory.getCurrentSession();
		Post p = session.get(Post.class, post.getPost_id());
		Blog b = session.get(Blog.class, blog_id);
		//SecurityUtil.authenticateUser(p.getUsername());
		SecurityUtil.checkIfBlogAdmin();
		
		p.setPost_name(post.getPost_name());
		p.setPost_author(post.getPost_author());
		p.setPosition(post.getPosition());
		p.setPost_modified(post.getPost_modified());
		//p.setBlog(b);
		if (post.getPost_html_path() != null && !"".equals(post.getPost_html_path())) {
			p.setPost_html_path(post.getPost_html_path());
		}
		p.setUsername(post.getUsername());

		session.update(p);
		
	}

	@Override
	public void deletePost(Integer post_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Post p = (Post) session.load(Post.class, post_id);
		//SecurityUtil.authenticateUser(p.getUsername());
		SecurityUtil.checkIfBlogAdmin();
		
		if(null != p){
			session.delete(p);
		}
	}
	
}
