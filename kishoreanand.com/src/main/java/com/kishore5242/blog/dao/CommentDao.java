package com.kishore5242.blog.dao;

import java.util.List;

import com.kishore5242.blog.entity.Comment;

public interface CommentDao {

	Comment save(Comment comment);

	List<Comment> findAll();

	Comment findById(Integer id);

	List<Comment> findAllByPostId(Integer post_id);

}
