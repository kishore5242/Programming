package com.kishore5242.blog.service;

import java.util.List;

import com.kishore5242.blog.entity.Comment;

public interface CommentService {

	Comment save(Comment comment);

	List<Comment> findAll();

	Comment findById(Integer id);

}
