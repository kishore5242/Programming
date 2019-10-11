package com.kishore5242.blog.dao;

import java.util.List;

import com.kishore5242.blog.entity.Blog;

public interface BlogDao {

	List<Blog> getAllBlogs();

	void addBlog(Blog blog);
	
	void removeBlog(Integer blog_id);

	Blog getBlog(Integer blog_id);
	
	void updateBlog(Blog blog);

	void deleteBlog(Integer blog_id);

}
