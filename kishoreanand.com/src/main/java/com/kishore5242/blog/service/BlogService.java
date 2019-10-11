package com.kishore5242.blog.service;

import java.util.List;

import com.kishore5242.blog.entity.Blog;

public interface BlogService {
	
	public void createBlog(Blog blog);
	
	public List<Blog> getAllBlogs();
	
	public void removeBlog(Integer blog_id);

	Blog getBlog(Integer blog_id);

	void updateBlog(Blog blog);
	
	public void deleteBlog(Integer blog_id);

}
