package com.kishore5242.blog.service;

import java.util.List;

import com.kishore5242.blog.entity.Post;

public interface PostService {
	
	public List<Post> getAllPosts();
	
	public List<Post> getAllPostsByBlog(Integer blog_id);
	
	public void removePost(Integer post_id);

	Post getPost(Integer post_id);

	void createPost(Post post, Integer blog_id);

	void updatePost(Post post, Integer blog_id);

	public void deletePost(Integer post_id);

	
}
