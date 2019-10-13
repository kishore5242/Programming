package com.kishore5242.blog.dao;

import java.util.List;

import com.kishore5242.blog.entity.Post;

public interface PostDao {

	List<Post> getAllPosts();
	
	List<Post> getAllPostsByBlog(Integer blog_id);
	
	void removePost(Integer post_id);

	Post getPost(Integer post_id);
	
	void updatePost(Post post, Integer blog_id);

	Integer addPost(Post post, Integer blog_id);

	void deletePost(Integer post_id);

}
