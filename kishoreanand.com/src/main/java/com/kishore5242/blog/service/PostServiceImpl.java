package com.kishore5242.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.blog.dao.PostDao;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.dao.StreamDao;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostDao postDao;

/*	@Override
	public void createTopic(Topic topic) {
		postDao.addTopic(topic);
	}*/

	@Override
	public List<Post> getAllPosts() {
		List<Post> allPosts = postDao.getAllPosts();
		//Collections.sort(allPosts);
		return allPosts;
	}
	
	@Override
	public List<Post> getAllPostsByBlog(Integer blog_id) {
		List<Post> allTopics = postDao.getAllPostsByBlog(blog_id);
		//Collections.sort(allTopics);
		return allTopics;
	}

	@Override
	public void removePost(Integer post_id) {
		postDao.removePost(post_id);
	}

	@Override
	public Post getPost(Integer post_id) {
		return postDao.getPost(post_id);
	}

	@Override
	public void updatePost(Post post, Integer blog_id) {
		postDao.updatePost(post, blog_id);
	}

	@Override
	public void createPost(Post post, Integer blog_id) {
				
		postDao.addPost(post, blog_id);

	}

	@Override
	public void deletePost(Integer post_id) {
		postDao.deletePost(post_id);
		
	}


}
