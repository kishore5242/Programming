package com.kishore5242.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.blog.dao.BlogDao;
import com.kishore5242.blog.entity.Blog;
import com.kishore5242.util.FileUtil;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	@Value("${files.upload.loc}")
    private String UPLOADED_FOLDER;
	

	@Override
	public List<Blog> getAllBlogs() {
		List<Blog> blogs = blogDao.getAllBlogs();
		return blogs;
	}

	@Override
	public void createBlog(Blog blog) {
		blogDao.addBlog(blog);
	}

	@Override
	public void removeBlog(Integer blog_id) {
		blogDao.removeBlog(blog_id);
	}

	@Override
	public Blog getBlog(Integer blog_id) {
		return blogDao.getBlog(blog_id);
	}

	@Override
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}

	@Override
	public void deleteBlog(Integer blog_id) {
		
		String blogDir = UPLOADED_FOLDER + blog_id+"/";
		
		FileUtil.deleteFolder(blogDir);
		
		blogDao.deleteBlog(blog_id);
		
	}

}
