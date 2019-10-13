package com.kishore5242.blog.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kishore5242.blog.entity.Post;

public interface PostService {
	
	public List<Post> getAllPosts();
	
	public List<Post> getAllPostsByBlog(Integer blog_id);
	
	public void removePost(Integer post_id);

	Post getPost(Integer post_id);

	public void deletePost(Integer blog_id, Integer post_id);

	void uploadPost(Integer blog_id, Post post, MultipartFile file) throws IOException;

	void updatePost(Post post, MultipartFile file) throws IOException;

	
}
